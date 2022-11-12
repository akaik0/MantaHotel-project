package rest;

import dao.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import resource.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CustomerRestResource extends RestResource {

    protected final String op;
    protected String response = null;
    protected final String[] tokens;

    public CustomerRestResource(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(req, res, con);
        op = req.getRequestURI();
        tokens = op.split("/");
    }

    public void listCustomers()  throws IOException {

        List<Customer> customerList  = null;
        Message m = null;

        try{
            String path = req.getRequestURI();
            //path = path.substring(path.lastIndexOf("customer") + 8);

            //final UUID customer = UUID.fromString(path.substring(1));

            customerList = new ListCustomersDatabase(con).listCustomers();

            if(customerList != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(customerList).toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot list customers: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search customers: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

    public void getCustomersByName() throws IOException {

        List<Customer> customerList  = null;
        Message m = null;

        try{
            String path = req.getRequestURI();
            String[] t = path.split("/");
            final String[] nameandsur = t[tokens.length-1].split("%20");
            final String name = nameandsur[0];
            final String surname = nameandsur[1];

            customerList = new ListCustomersByNameDatabase(con, name, surname).listCustomersByName();

            if(customerList != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(customerList).toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot list customers: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search customers: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
