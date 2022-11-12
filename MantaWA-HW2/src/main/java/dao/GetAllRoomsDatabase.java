package dao;

import resource.Room;
import resource.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GetAllRoomsDatabase {
    private static final String STATEMENT = "SELECT * FROM room;";

    private final Connection con;

    public GetAllRoomsDatabase(final Connection con) {
        this.con = con;
    }

    public List<Room> getAllRooms() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Room> allRooms = new ArrayList<Room>();


        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                allRooms.add(new Room(rs.getString(1), rs.getInt(2),
                        rs.getDouble(3), RoomType.valueOf(rs.getString(4).
                        toUpperCase(Locale.ROOT).replace(' ','_')),
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
        return allRooms;
    }
}
