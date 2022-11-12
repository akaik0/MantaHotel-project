package dao;

import java.sql.*;
import java.util.UUID;

public class AddCustomerToBookingDatabase {

    private static final String STATEMENT = "INSERT INTO staying VALUES (?, ?, ?) RETURNING personId;";

    private final Connection con;

    UUID booking;
    UUID customer;
    String room;

    public AddCustomerToBookingDatabase(final Connection con, final UUID b, final UUID c, final String r) {
        this.con = con;
        this.booking = b;
        this.customer = c;
        this.room = r;
    }

    public UUID addCustomerToBooking() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        UUID c = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, booking);
            pstmt.setObject(3, customer);
            pstmt.setString(2, room);

            rs = pstmt.executeQuery();

            if (rs.next()){
                c = UUID.fromString(rs.getString(1));
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
        return c;
    }
}
