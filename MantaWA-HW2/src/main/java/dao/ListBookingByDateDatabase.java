package dao;

import resource.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

public class ListBookingByDateDatabase {

    private static final String STATEMENT = "SELECT * FROM booking WHERE checkin::date = ? OR checkout::date = ?;";

    private final Connection con;
    Date date;

    public ListBookingByDateDatabase(final Connection con, final Date c) {
        this.con = con;
        this.date = c;
    }

    public List <Booking> listBookingsByDate() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Booking> foundBookings = new ArrayList<Booking>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, date);
            pstmt.setObject(2, date);

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



