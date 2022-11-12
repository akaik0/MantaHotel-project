package dao;

import resource.Log;

import java.sql.*;

public class InsertLogDatabase {

    private static final String STATEMENT = "INSERT INTO log VALUES (?, ?, ?) RETURNING *;";

    private final Connection con;

    Log log;

    public InsertLogDatabase(final Connection con, final Log l) {
        this.con = con;
        this.log = l;
    }

    public void insertLog() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, log.getEmail());
            pstmt.setTimestamp(2, log.getLogin());
            pstmt.setTimestamp(3, log.getLogout());

            rs = pstmt.executeQuery();
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

}
