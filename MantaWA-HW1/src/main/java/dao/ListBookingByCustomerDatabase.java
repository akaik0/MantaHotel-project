package dao;

import resource.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

public class ListBookingByCustomerDatabase {

    private static final String STATEMENT = "SELECT * FROM booking WHERE personid=?;";

    private final Connection con;
    UUID customer;

    public ListBookingByCustomerDatabase(final Connection con, final UUID c) {
        this.con = con;
        this.customer = c;
    }

    public List <Booking> listBookingsByCustomer() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Booking> foundBookings = new ArrayList<Booking>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, customer);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                foundBookings.add(new Booking((UUID) rs.getObject(1), (UUID) rs.getObject(2),
                        rs.getTimestamp(3), rs.getTimestamp(4), (UUID) rs.getObject(5),
                        rs.getString(7)));
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



