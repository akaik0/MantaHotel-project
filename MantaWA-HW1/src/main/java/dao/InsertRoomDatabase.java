package dao;

import resource.Room;
import resource.RoomType;

import java.sql.*;
import java.util.Locale;

public class InsertRoomDatabase {

    private static final String STATEMENT = "INSERT INTO room VALUES (?, ?, ?, ?::category, ?) RETURNING *;";

    private final Connection con;

    Room room;

    public InsertRoomDatabase(final Connection con, final Room r) {
        this.con = con;
        this.room = r;
    }

    public Room insertRoom() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Room newRoom = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, room.getRoomNumber());
            pstmt.setInt(2, room.getBeds());
            pstmt.setDouble(3, room.getPrice());
            pstmt.setString(4, room.getRoomType().getName());
            pstmt.setString(5, room.getDescription());

            rs = pstmt.executeQuery();

            if(rs.next()) {
                newRoom = new Room(rs.getString(1), rs.getInt(2), rs.getDouble(3),
                        RoomType.valueOf(rs.getString(4).toUpperCase(Locale.ROOT).replace(' ', '_')),
                        rs.getString(5));
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
        return newRoom;
    }
}
