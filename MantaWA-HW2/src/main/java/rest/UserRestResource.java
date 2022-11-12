package rest;

import dao.*;
import resource.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserRestResource extends RestResource{
    public UserRestResource(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(req, res, con);
    }

    public void createUser() throws IOException {

        User u  = null;
        Message_new m = null;

        try{

            final User user =  User.fromJSON(req.getInputStream());

            // creates a new object for accessing the database and stores the employee
            u = new InsertUserDatabase(con, user).insertUser();

            if(u != null) {
                res.setStatus(HttpServletResponse.SC_CREATED);
                m = new Message_new("Success",
                        "200", "User inserted successfully");
                m.toJSON(res.getOutputStream());
            } else {
                // it should not happen
                m = new Message_new("JSON Error",
                        "E4A1", "JSON Error");
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23505")) {
                m = new Message_new("Duplicate User",
                        "110", "User already exist");
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message_new("Unexpected Error",
                        "E4A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }

    public void listUsers() throws IOException, SQLException {

        List<User> el  = null;
        List<User> ul  = null;
        Message_new m = null;
        int error = 1;

        try{
            // creates a new object for accessing the database and lists all the employees
            el = new ListUsersDatabase(con).listUsers();

            if(el != null) {
                ul = new ListUsersDatabase(con).listUsers();
                error = 2;
                if (ul != null) {
                    error = 3;
                    res.setStatus(HttpServletResponse.SC_OK);
                    new ResourceList(ul).toJSON(res.getOutputStream());
                } else {
                    // it should not happen
                    m = new Message_new("Cannot list employees: unexpected error.", "E5A1", null);
                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    m.toJSON(res.getOutputStream());
                }
            }
        } catch (Throwable t) {
            m = new Message_new("Cannot search employee: unexpected error." + error, "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
