package dao;

import resource.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ListBookingNotPayed {

    private static final String STATEMENT = "SELECT booking.* FROM booking join payment on booking.paymentid=payment.paymentid WHERE complete=false;";

    private final Connection con;
    Date date;

    public ListBookingNotPayed(final Connection con) {
        this.con = con;
    }

    public List <Booking> listBookingsNotPayed() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Booking> foundBookings = new ArrayList<Booking>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

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



