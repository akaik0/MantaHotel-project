package dao;

import resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListUsersDatabase {

    private static final String STATEMENT = "SELECT * FROM hoteluser;";
    private final Connection con;

    public ListUsersDatabase(final Connection con) {
        this.con = con;
    }

    public List<User> listUsers() throws SQLException {

        System.out.println("Inside method listUsers()  ");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> usersList = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                usersList.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            System.out.println("User list complete " + usersList);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return usersList;
    }
}
