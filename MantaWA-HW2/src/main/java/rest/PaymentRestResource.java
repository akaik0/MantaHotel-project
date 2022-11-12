package rest;

import dao.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import resource.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.UUID;

public class PaymentRestResource extends RestResource {

    protected final String op;
    protected String response = null;
    protected final String[] tokens;

    public PaymentRestResource(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(req, res, con);
        op = req.getRequestURI();
        tokens = op.split("/");
    }

    public void getPaymentListByCustomer()  throws IOException {

        List<Payment> paymentList  = null;
        Message m = null;

        try{
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("customer") + 8);

            final UUID customer = UUID.fromString(path.substring(1));

            paymentList = new ListPaymentsByCustomerDatabase(con, customer).listPaymentsByCustomer();

            if(paymentList != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(paymentList).toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot list payments: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search payment: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

    public void getPaymentByBooking() throws IOException {

        Payment p  = null;
        Message m = null;

        try{
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("booking") + 7);

            final UUID booking = UUID.fromString(path.substring(1));

            p = new GetPaymentByBookingDatabase(con, booking).getPaymentByBooking();

            if(p != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                p.toJSON(res.getOutputStream());
            } else {
                m = new Message("Payment not found.", "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search payment: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
