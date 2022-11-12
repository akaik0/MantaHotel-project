package dao;

import resource.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/** Get the log info of every user*/
public class ListLogsDatabase {

    private static final String STATEMENT = "SELECT * FROM Log WHERE login::date = ?::date;"; //maybe have to change the =

    private Connection con;

    public ListLogsDatabase(final Connection con) {
        this.con = con;
    }

    public List<Log> listLogs() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Log> logList=new ArrayList<>();

        String curr_day = String.valueOf(java.time.LocalDate.now());

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, curr_day);

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


