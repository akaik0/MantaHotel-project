package dao;

import resource.RoomType;
import resource.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class GetRoomByNumberDatabase {

    private static final String STATEMENT = "SELECT * FROM room WHERE roomnumber=?;";

    private final Connection con;
    String room;

    public GetRoomByNumberDatabase(final Connection con, final String r) {
        this.con = con;
        this.room = r;
    }

    public Room getRoomByNumber() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Room searchedRoom = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, room);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                searchedRoom = new Room(rs.getString(1), rs.getInt(2),
                        rs.getDouble(3), RoomType.valueOf(rs.getString(4).
                        toUpperCase(Locale.ROOT).replace(' ','_')), rs.getString(5));
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
