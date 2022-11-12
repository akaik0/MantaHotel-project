package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GetCustomerIdByEmailAndPasswordDatabase {

    private static final String STATEMENT = "SELECT personId FROM customer WHERE email=? AND password=?;";

    private final Connection con;
    String email, password;

    public GetCustomerIdByEmailAndPasswordDatabase(final Connection con, final String e, final String pw) {
        this.con = con;
        this.email = e;
        this.password = pw;
    }

    public UUID getCustomerIdByEmailAndPassword() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UUID searchedCustomer = null;

        System.out.println("GET CUSTOMER BY MAIL AND PW:   " + email + " " + password);

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, email);
            pstmt.setString(2,password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                searchedCustomer = (UUID) rs.getObject(1);
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

        return searchedCustomer;
    }
}
