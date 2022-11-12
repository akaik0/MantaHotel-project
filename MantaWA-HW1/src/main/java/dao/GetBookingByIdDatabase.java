package dao;

import resource.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GetBookingByIdDatabase {

    private static final String STATEMENT = "SELECT * FROM booking WHERE bookingid=?;";

    private final Connection con;

    Booking booking;

    public GetBookingByIdDatabase(final Connection con, final Booking b) {
        this.con = con;
        this.booking = b;
    }

    public Booking getBookingById() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Booking searchedBooking = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, booking.getBookingid());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                searchedBooking = new Booking((UUID) rs.getObject(1), (UUID) rs.getObject(2),
                        rs.getTimestamp(3), rs.getTimestamp(4), (UUID) rs.getObject(5),
                        rs.getString(7));
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

        return searchedBooking;
    }
}


