package dao;

import resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

public class ListCustomersByBookingDatabase {

    private static final String STATEMENT = "SELECT customer.name, customer.surname, customer.personId" +
            " FROM customer JOIN staying ON staying.personId = customer.personId WHERE staying.bookingId=?;";

    private final Connection con;
    UUID booking;

    public ListCustomersByBookingDatabase(final Connection con, final UUID b) {
        this.con = con;
        this.booking = b;
    }

    public List <Customer> listCustomersByBooking() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Customer> foundBookings = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, booking);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                foundBookings.add(new Customer((UUID) rs.getObject(3), rs.getString(1),
                        rs.getString(2)));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return foundBookings;
    }
}




