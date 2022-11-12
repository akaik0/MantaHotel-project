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
import java.util.*;

import java.time.temporal.ChronoUnit;

public class PaymentServlet extends AbstractServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("payment/")+8);

        switch (op){
            case "searchByBooking": {

                UUID bookingId = UUID.fromString(req.getParameter("bookingid"));
                System.out.println("INSIDE SEARCH BY BOOKING");
                try {
                    Payment payment = new GetPaymentByBookingDatabase(getDataSource().getConnection(), bookingId).getPaymentByBooking();
                    System.out.println("Got payment:  " + payment);
                    if (payment == null) {
                        ErrorCode ec = ErrorCode.PAYMENT_NOT_FOUND;
                        Message m = new Message(true, ec.getErrorMessage());
                        req.setAttribute("message", m);
                    }
                    req.setAttribute("payment", payment);
                    req.getRequestDispatcher("/jsp/user-area/payment.jsp").forward(req, res);
                } catch (NamingException | SQLException e) {
                    writeError(res, ErrorCode.INTERNAL_ERROR);
                }
                break;
            }

            case "list": {
                String personId = req.getParameter("personid");

                if (personId == null || personId.equals("")) {
                    writeError(res, ErrorCode.PERSONID_NOT_PROVIDED);
                }

                List<Payment> payments = null;
                Customer customer = null;

                try {
                    payments = new ListPaymentsByCustomerDatabase(getDataSource().getConnection(),
                            UUID.fromString(personId)).listPaymentsByCustomer();
                    customer = new GetCustomerByIdDatabase(getDataSource().getConnection(),
                            UUID.fromString(personId)).getCustomerById();
                } catch (SQLException | NamingException e) {
                    Message m = new Message(true, "No payments found");
                    ErrorCode ec = ErrorCode.PAYMENT_NOT_FOUND;
                    res.setStatus(ec.getHTTPCode());
                    req.setAttribute("message", m);
                }
                req.setAttribute("paymentList", payments);
                req.setAttribute("customer", customer);
                res.setStatus(200);
                req.getRequestDispatcher("/jsp/user-area/payment.jsp").forward(req, res);
                break;
            }

            case "searchID":{

                String paymentId = req.getParameter("paymentid");
                ErrorCode ec = null;
                Message m = null;

                if (paymentId == null || paymentId.equals("")) {
                    ec = ErrorCode.EMPTY_INPUT_FIELDS;
                    m = new Message(true, ec.getErrorMessage());
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/user_area/payment.jsp").forward(req,res);
                    break;
                }
                UUID paymentid = UUID.fromString(paymentId);
                Payment p = null;
                try {
                    p = new GetPaymentByIdDatabase(getDataSource().getConnection(), paymentid).getPaymentById();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }
                req.setAttribute("payment", p);
                req.getRequestDispatcher("/jsp/user_area/payment.jsp").forward(req,res);
                break;
            }
            default:
                Message m = new Message(true, "Url not found ".concat(op));
                res.setStatus(404);
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/payment.jsp").forward(req, res);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String op = req.getRequestURI().split("/", 4)[3];

        switch (op){
            case "edit":{

                String paymentId = req.getParameter("paymentid");
                String pm = req.getParameter("paymentM");
                String amount = req.getParameter("amount");
                String payed = req.getParameter("complete");

                UUID paymentid = UUID.fromString(paymentId);

                try {
                    Payment oldp = new GetPaymentByIdDatabase(getDataSource().getConnection(), paymentid).getPaymentById();

                    PaymentMethod paym = oldp.getPaymentMethod();
                    if((pm != null) && !(pm.equals(""))){
                        paym = PaymentMethod.valueOf(pm.toUpperCase(Locale.ROOT).replace(' ', '_'));
                    }

                    double tot = oldp.getTotalAmount();
                    if((amount != null) && !(amount.equals(""))){
                        tot = Double.parseDouble(amount);
                    }

                    boolean complete = oldp.isComplete();
                    if((amount != null) && !(amount.equals(""))){
                        complete = Boolean.parseBoolean(payed);
                    }

                    Payment newp = new Payment(oldp.getPaymentID(), tot, paym, oldp.getDate(), complete, oldp.getEmail());

                    Payment p = new UpdatePaymentDatabase(getDataSource().getConnection(), newp).updatePayment();


                    req.setAttribute("payment", p);
                    req.getRequestDispatcher("/jsp/user-area/payment.jsp").forward(req,res);
                    break;

                } catch (SQLException | NamingException | NullPointerException e) {
                    e.printStackTrace();
                }

            }
            default:
                writeError(res, ErrorCode.OPERATION_UNKNOWN);
        }

    }
}
