package dao;

import resource.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/** Get the log info of every user*/
public class ListLogsByUserAndDateDatabase {

    private static final String STATEMENT = "SELECT * FROM Log WHERE hoteluser = ? AND login::date = ?::date;";

    private Connection con;
    private String email;
    private String day;

    public ListLogsByUserAndDateDatabase(final Connection con, final String email, final String day) {
        this.con = con;
        this.email = email;
        this.day = day;
    }

    public List<Log> listLogsByUserAndDate() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Log> logList=new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, email);
            pstmt.setString(2, day);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                logList.add(new Log(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3)));
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

        return logList;
    }

}


