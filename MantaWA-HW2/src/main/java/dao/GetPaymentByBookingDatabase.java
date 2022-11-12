
package dao;

import resource.Payment;
import resource.PaymentMethod;
import resource.Room;
import resource.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

public class GetPaymentByBookingDatabase {

    private static final String STATEMENT ="SELECT * FROM payment JOIN booking ON payment.paymentid = booking.paymentid WHERE bookingid=?;";

    private final Connection con;

    UUID booking;

    public GetPaymentByBookingDatabase(final Connection con, final UUID b) {
        this.con = con;
        this.booking = b;
    }

    public Payment getPaymentByBooking() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Payment searchedPayment = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, booking);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                searchedPayment = new Payment(UUID.fromString(rs.getString(1)), rs.getDouble(2),
                        PaymentMethod.valueOf(rs.getString(3).toUpperCase(Locale.ROOT).replace(' ', '_')),
                        rs.getDate(4), rs.getBoolean(5), rs.getString(6));
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

        return searchedPayment;
    }
}


