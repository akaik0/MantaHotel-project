package dao;

import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateUserNotPasswordDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE hoteluser SET name=?, surname=?, rolename=?::roletype WHERE email=? RETURNING *;";
    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The user to be searched
     */
    User user;

    public UpdateUserNotPasswordDatabase(final Connection con, final User u) {
        this.con = con;
        this.user = u;
    }

    public User updateUserNotPassword() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the created user
        User newUser = null;
        System.out.println("inside dao");
        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, user.getFirst_name());
            pstmt.setString(2, user.getLast_name());
            pstmt.setString(3, user.getRole());
            pstmt.setString(4, user.getEmail());

            System.out.println("got the strings");

            rs = pstmt.executeQuery();
            System.out.println("executed query");

            if (rs.next()) {
                newUser = new User(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
            }

            System.out.println("new user    " + newUser);

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return newUser;
    }

}
