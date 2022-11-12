package servlet;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import resource.*;
import utils.ErrorCode;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.time.temporal.ChronoUnit;

public class BookingServlet extends AbstractServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("booking/")+8);

        switch (op){
            case "search": {

                String bookingId = req.getParameter("bookingId");

                try {
                    Booking booking = new Booking(UUID.fromString(bookingId));

                    booking = new GetBookingByIdDatabase(getDataSource().getConnection(), booking).getBookingById();
                    if (booking == null) {
                        Message m = new Message(true, "Booking not found");
                        req.setAttribute("message", m);
                    } else {
                        req.setAttribute("booking", booking);
                    }
                    res.setStatus(200);
                    req.getRequestDispatcher("/jsp/search-booking-result.jsp").forward(req, res);
                } catch (NamingException | SQLException e) {
                    writeError(res, ErrorCode.INTERNAL_ERROR);
                }
                break;
            }

            case "list": {
                String personId = req.getParameter("customer");

                if (personId == null || personId.equals("")) {
                    writeError(res, ErrorCode.PERSONID_NOT_PROVIDED);
                }

                List<Booking> bookings = null;
                Customer customer = null;

                try {
                    bookings = new ListBookingByCustomerDatabase(getDataSource().getConnection(),
                            UUID.fromString(personId)).listBookingsByCustomer();
                    customer = new GetCustomerByIdDatabase(getDataSource().getConnection(),
                            UUID.fromString(personId)).getCustomerById();
                } catch (SQLException | NamingException e) {
                    Message m = new Message(true, "No bookings found");
                    ErrorCode ec = ErrorCode.BOOKING_NOT_FOUND;
                    res.setStatus(ec.getHTTPCode());
                    req.setAttribute("message", m);
                }
                req.setAttribute("bookingList", bookings);
                req.setAttribute("customer", customer);
                res.setStatus(200);
                req.getRequestDispatcher("/jsp/search-booking-result.jsp").forward(req, res);
                break;
            }
            case "list/date": {
                String date = req.getParameter("date");

                if (date == null || date.equals("")) {
                    writeError(res, ErrorCode.DATE_NOT_PROVIDED);
                }

                List<Booking> bookings = null;

                try {
                    bookings = new ListBookingByDateDatabase(getDataSource().getConnection(),
                            Date.valueOf(date)).listBookingsByDate();
                } catch (SQLException | NamingException e) {
                    Message m = new Message(true, "No bookings found");
                    ErrorCode ec = ErrorCode.BOOKING_NOT_FOUND;
                    res.setStatus(ec.getHTTPCode());
                    req.setAttribute("message", m);
                }
                req.setAttribute("bookingList", bookings);
                req.setAttribute("date", date);
                res.setStatus(200);
                req.getRequestDispatcher("/jsp/search-booking-result.jsp").forward(req, res);
                break;
            }
            case "list/notpayed": {

                List<Booking> bookings = null;

                try {
                    bookings = new ListBookingNotPayed(getDataSource().getConnection()).listBookingsNotPayed();
                } catch (SQLException | NamingException e) {
                    Message m = new Message(true, "No bookings found");
                    ErrorCode ec = ErrorCode.BOOKING_NOT_FOUND;
                    res.setStatus(ec.getHTTPCode());
                    req.setAttribute("message", m);
                }
                req.setAttribute("bookingNotPayed", bookings);
                req.setAttribute("notpayed", 1);
                res.setStatus(200);
                req.getRequestDispatcher("/jsp/search-booking-result.jsp").forward(req, res);
                break;
            }
            case "customer-list":{
                UUID personId = (UUID) req.getSession().getAttribute("personId");
                List<Booking> bookings = null;

                try {
                    bookings = new ListBookingByCustomerDatabase(getDataSource().getConnection(),
                            personId).listBookingsByCustomer();

                } catch (SQLException | NamingException e) {
                    Message m = new Message(true, "No bookings found");
                    ErrorCode ec = ErrorCode.BOOKING_NOT_FOUND;
                    res.setStatus(ec.getHTTPCode());
                    req.setAttribute("message", m);
                }
                req.setAttribute("bookingList", bookings);
                res.setStatus(200);
                req.getRequestDispatcher("/jsp/customer-bookings.jsp").forward(req, res);
                break;
            }

            default:
                Message m = new Message(true, "Url not found ".concat(op));
                res.setStatus(404);
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/customer-bookings.jsp").forward(req, res);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("booking/")+8);

        switch (op){
            case "insert/info":{

                String checkin = req.getParameter("checkin");
                String checkout = req.getParameter("checkout");
                String beds = req.getParameter("beds");
                String paymentMethod = req.getParameter("paymentM");
                String requests = req.getParameter("requests");

                ErrorCode ec = null;
                Message m = null;
                String dispatchPage = null;
                HttpSession session = req.getSession(false);

                if (checkin == null || checkin.equals("") || checkout == null || checkout.equals("")
                        || beds == null || beds.equals("") || paymentMethod == null || paymentMethod.equals("")) {
                    ec = ErrorCode.EMPTY_INPUT_FIELDS;
                    m = new Message(true, ec.getErrorMessage());
                    dispatchPage = "/jsp/insert-booking-form.jsp";
                } else {
                    String[] tokens = checkin.split("T");
                    checkin = tokens[0]+" "+tokens[1] +":00";
                    tokens = checkout.split("T");
                    checkout = tokens[0]+" "+tokens[1] +":00";
                    Timestamp cin = Timestamp.valueOf(checkin);
                    Timestamp cout = Timestamp.valueOf(checkout);
                    UUID paymentId = UUID.randomUUID();

                    UUID personId = (UUID) session.getAttribute("personId");
                    String user = null;
                    if (personId == null) {
                        // if we are here it means that the booking is made by a user not a customer
                        personId = UUID.fromString(req.getParameter("personId"));
                        user = (String) session.getAttribute("email");
                    }

                    // create new Booking
                    session.setAttribute("booking", new Booking(UUID.randomUUID(),
                            personId, cin, cout, paymentId, requests));

                    //session.setAttribute("paymentM", paymentMethod);
                    PaymentMethod pm = PaymentMethod.valueOf(paymentMethod.toUpperCase(Locale.ROOT).replace(' ', '_'));

                    session.setAttribute("payment", new Payment(paymentId, pm, user));

                    List<Room> roomList = null;

                    try {
                        roomList = new ListRoomByDateDatabase(getDataSource().getConnection(),
                                Integer.parseInt(beds), cin, cout).listRoomByDate();
                    } catch (SQLException | NamingException e) {
                        e.printStackTrace();
                    }

                    req.setAttribute("roomList", roomList);

                    req.getRequestDispatcher("/jsp/booking-room.jsp").forward(req, res);

                }
                req.setAttribute("message", m);
                req.getRequestDispatcher(dispatchPage).forward(req, res);
                break; }
            case "insert/complete": {

                HttpSession session = req.getSession(false);

                Booking booking = (Booking) session.getAttribute("booking");

                Room room = null;
                try {
                    room = new GetRoomByNumberDatabase(getDataSource().getConnection(), req.getParameter("room")).getRoomByNumber();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }

                // compute total amount of payment
                Timestamp checkin = booking.getCheckin();
                Timestamp checkout = booking.getCheckout();
                double totAmount = room.getPrice() * (ChronoUnit.DAYS.between(checkin.toLocalDateTime(), checkout.toLocalDateTime()));


                Payment payment = (Payment) session.getAttribute("payment");
                payment = new Payment(payment.getPaymentID(), payment.getPaymentMethod(),
                        totAmount, payment.getEmail()); // same as above but with total amount added;

                Booking b = null;
                Payment p = null;
                Room r = null;

                try {
                    b = new InsertCompleteBookingDatabase(getDataSource().getConnection(), payment, booking, room).insertBooking();
                    p = new GetPaymentByIdDatabase(getDataSource().getConnection(), payment.getPaymentID()).getPaymentById();
                    r = new GetRoomByBookingDatabase(getDataSource().getConnection(), b.getBookingid()).getRoomByBooking();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }
                if (b == null || p == null) {
                    Message m = new Message(true, "Errors in booking insertion");
                    req.setAttribute("message", m);
                } else {
                    req.setAttribute("booking", b);
                    req.setAttribute("payment", p);
                    req.setAttribute("room", r);
                }
                req.getRequestDispatcher("/jsp/insert-booking-result.jsp").forward(req,res);
                break;
            }
            case "add-customer": {

                String personId = req.getParameter("personId");
                String bookingId = req.getParameter("bookingId");

                if (personId == null || personId.equals("") || bookingId == null || bookingId.equals("")) {
                    ErrorCode ec = ErrorCode.EMPTY_INPUT_FIELDS;
                    Message m = new Message(true, ec.getErrorMessage());
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/add-customer-booking.jsp").forward(req, res);
                } else {
                    UUID booking = UUID.fromString(bookingId);

                    // room needed to insert in Staying table
                    Room room = null;
                    try {
                        room = new GetRoomByBookingDatabase(getDataSource().getConnection(), booking).getRoomByBooking();
                    } catch (SQLException | NamingException e) {
                        e.printStackTrace();
                    }

                    Customer addedCustomer = null;
                    try {
                        UUID id = new AddCustomerToBookingDatabase(getDataSource().getConnection(),
                                booking, UUID.fromString(personId), room.getRoomNumber()).addCustomerToBooking();
                        addedCustomer = new GetCustomerByIdDatabase(getDataSource().getConnection(), id).getCustomerById();
                    } catch (SQLException | NamingException e) {
                        e.printStackTrace();
                    }

                    // list all the customers related to this booking
                    List<Customer> customerList = null;
                    try {
                        customerList = new ListCustomersByBookingDatabase(getDataSource().getConnection(),
                                booking).listCustomersByBooking();
                    } catch (SQLException | NamingException e) {
                        e.printStackTrace();
                    }

                    req.setAttribute("addedCustomer", addedCustomer);
                    req.setAttribute("bookingId", booking);
                    req.setAttribute("customerList", customerList);

                    req.getRequestDispatcher("/jsp/add-customer-booking.jsp").forward(req, res);

                }
                break;
            }
            default:
                writeError(res, ErrorCode.OPERATION_UNKNOWN);
                logger.warn("requested op "+op);
        }

    }
}
