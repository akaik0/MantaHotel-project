package dao;

import resource.Booking;
import resource.Payment;
import resource.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class InsertCompleteBookingDatabase {

    private static final String STATEMENT1 = "INSERT INTO payment (paymentid, totalamount, method, date, complete, hoteluser) VALUES (?,?,?::paymentmethod,?,?,?) RETURNING *;";
    private static final String STATEMENT2 = "INSERT INTO booking (bookingid, personid, checkin, checkout, paymentid, date, requests) VALUES (?,?,?,?,?,?,?) RETURNING *;";
    private static final String STATEMENT3 = "INSERT INTO staying (bookingid, roomnumber, personid) VALUES (?,?,?) RETURNING *;";

    private final Connection con;
    Payment payment;
    Booking booking;
    Room room;

    public InsertCompleteBookingDatabase(final Connection con, final Payment p, final Booking b, final Room r) {
        this.con = con;
        this.payment = p;
        this.booking = b;
        this.room = r;
    }

    public Booking insertBooking() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Booking newBooking = null;


        try {
            // payment
            pstmt = con.prepareStatement(STATEMENT1);

            pstmt.setObject(1, payment.getPaymentID());
            pstmt.setDouble(2, payment.getTotalAmount());
            pstmt.setString(3, payment.getPaymentMethod().getName());
            pstmt.setDate(4, payment.getDate());
            pstmt.setBoolean(5, false);
            pstmt.setString(6, payment.getEmail());

            pstmt.execute();

            // booking
            pstmt = con.prepareStatement(STATEMENT2);

            pstmt.setObject(1, booking.getBookingid());
            pstmt.setObject(2, booking.getPersonid());
            pstmt.setTimestamp(3, booking.getCheckin());
            pstmt.setTimestamp(4, booking.getCheckout());
            pstmt.setObject(5, booking.getPaymentid());
            pstmt.setDate(6, booking.getDate());
            pstmt.setString(7, booking.getRequests());

            rs = pstmt.executeQuery();

            if (rs.next()) { //rs.getObject(1,UUID.class)
                newBooking = new Booking( (UUID) rs.getObject(1), (UUID) rs.getObject(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), (UUID) rs.getObject(5), rs.getString(7));
            }


            // staying
            pstmt = con.prepareStatement(STATEMENT3);

            pstmt.setObject(1, booking.getBookingid());
            pstmt.setString(2, room.getRoomNumber());
            pstmt.setObject(3, booking.getPersonid());

            pstmt.execute();

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return newBooking;
    }

}


