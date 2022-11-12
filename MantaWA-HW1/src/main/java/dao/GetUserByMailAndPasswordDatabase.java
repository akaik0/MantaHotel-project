package dao;

import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserByMailAndPasswordDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT * FROM hoteluser WHERE email=? AND password=?;";
    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The user to be searched
     */
    User user;

    public GetUserByMailAndPasswordDatabase(final Connection con, final User u) {
        this.con = con;
        this.user = u;
    }


    public User getUserByMailAndPassword() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User foundUser=null;


        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());

            rs = pstmt.executeQuery();

            System.out.println("GET USER BY MAIL AND PW:   " + rs);

            if (rs.next()) {
                foundUser = new User(rs.getString(1), rs.getString(2),
                                     rs.getString(3), rs.getString(4), rs.getString(5));
            }
            System.out.println("FOUND USER:   " + foundUser);

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return foundUser;
    }
}
