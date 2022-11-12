
package dao;

import resource.Room;
import resource.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

public class GetRoomByBookingDatabase {

    private static final String STATEMENT = "SELECT room.roomnumber, room.beds, room.roomtype FROM booking JOIN staying ON staying.bookingid = booking.bookingid JOIN room ON room.roomnumber = staying.roomnumber WHERE booking.bookingid=?;";

    private final Connection con;

    UUID booking;

    public GetRoomByBookingDatabase(final Connection con, final UUID b) {
        this.con = con;
        this.booking = b;
    }

    public Room getRoomByBooking() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Room searchedRoom = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, booking);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                searchedRoom = new Room(rs.getString(1), rs.getInt(2),
                        RoomType.valueOf(rs.getString(3).toUpperCase(Locale.ROOT).replace(' ', '_')));
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

        return searchedRoom;
    }
}


