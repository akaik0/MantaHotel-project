package dao;

import resource.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DeleteBookingDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "DELETE FROM booking WHERE bookingid=? RETURNING *;";
    /**
     * The connection to the database
     */
    private final Connection con;

    Booking booking;

    public DeleteBookingDatabase(final Connection con, final Booking b) {
        this.con = con;
        this.booking = b;
    }

    public Booking deleteBooking() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Booking deletedBooking = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, booking.getBookingid());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                deletedBooking = new Booking((UUID) rs.getObject(1), (UUID) rs.getObject(2),
                        rs.getTimestamp(3), rs.getTimestamp(4), (UUID) rs.getObject(5),
                        rs.getString(6));
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

        return deletedBooking;
    }
}

