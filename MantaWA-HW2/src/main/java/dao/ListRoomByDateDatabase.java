package dao;

import resource.RoomType;
import resource.Room;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListRoomByDateDatabase {

    private static final String STATEMENT = "SELECT * FROM room WHERE beds = ? AND roomnumber NOT IN " +
            "(SELECT room.roomnumber FROM booking JOIN staying ON booking.bookingid = staying.bookingid " +
            "JOIN room ON staying.roomnumber = room.roomnumber WHERE (booking.checkin::date BETWEEN " +
            "?::date AND ?::date) OR (booking.checkout::date BETWEEN ?::date" +
            " AND ?::date ));";

    private final Connection con;
    int beds;
    Timestamp cin, cout;


    public ListRoomByDateDatabase(final Connection con, final int beds, final Timestamp cin, final Timestamp cout) {
        this.beds = beds;
        this.con = con;
        this.cin = cin;
        this.cout = cout;
    }

    public List <Room> listRoomByDate() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Room> foundRooms = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setInt(1, beds);
            pstmt.setTimestamp(2, cin);
            pstmt.setTimestamp(3, cout);
            pstmt.setTimestamp(4, cin);
            pstmt.setTimestamp(5, cout);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                foundRooms.add(new Room(rs.getString(1), rs.getInt(2), rs.getDouble(3),
                        RoomType.valueOf(rs.getString(4).toUpperCase(Locale.ROOT).replace(' ','_')),
                        rs.getString(5)));
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
        return foundRooms;
    }
}



