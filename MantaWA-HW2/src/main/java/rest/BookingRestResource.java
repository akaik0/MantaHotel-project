package rest;

import dao.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import resource.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.UUID;

public class BookingRestResource extends RestResource {

    protected final String op;
    protected String response = null;
    protected final String[] tokens;

    public BookingRestResource(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(req, res, con);
        op = req.getRequestURI();
        tokens = op.split("/");
    }

    public void getBookingListByCustomer()  throws IOException {

        List<Booking> bookingList  = null;
        Message m = null;

        try{
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("customer") + 8);

            final UUID customer = UUID.fromString(path.substring(1));

            bookingList = new ListBookingByCustomerDatabase(con, customer).listBookingsByCustomer();

            if(bookingList != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(bookingList).toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot list bookings: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search booking: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

    public void getBookingListByDate() throws IOException {

        List<Booking> bookingList  = null;
        Message m = null;

        try{
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("date") + 4);

            final Date date = Date.valueOf(path.substring(1));

            bookingList = new ListBookingByDateDatabase(con, date).listBookingsByDate();

            if(bookingList != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(bookingList).toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot list bookings: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search booking: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

    public void getBooking() throws IOException {

        Booking b  = null;
        Message m = null;

        try{
            String path = req.getRequestURI();

            String[] t = path.split("/");
            final UUID bookingid = UUID.fromString(t[tokens.length-1]);

            b = new GetBookingByIdDatabase(con, new Booking(bookingid)).getBookingById();

            if(b != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                b.toJSON(res.getOutputStream());
            } else {
                m = new Message("Booking " + bookingid.toString() + " not found.", "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot read booking: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

    public void insertBooking() throws IOException {

        Booking b  = null;
        Message m = null;

        try{
            Booking booking = (Booking) req.getAttribute("booking");
            Payment payment = (Payment) req.getAttribute("payment");
            Room room = (Room) req.getAttribute("room");

            b = new InsertCompleteBookingDatabase(con, payment, booking, room).insertBooking();

            if(b != null) {
                res.setStatus(HttpServletResponse.SC_CREATED);
                b.toJSON(res.getOutputStream());
                payment.toJSON(res.getOutputStream());
                room.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot insert the booking: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot create the booking: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
