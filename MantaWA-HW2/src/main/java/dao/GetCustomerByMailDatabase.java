package dao;

import resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GetCustomerByMailDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT * FROM customer WHERE email=?;";
    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The user to be searched
     */
    Customer customer;

    public GetCustomerByMailDatabase(final Connection con, final Customer c) {
        this.con = con;
        this.customer = c;
    }


    public Customer getCustomerByMail() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer foundCustomer=null;


        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, customer.getEmail());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                foundCustomer = new Customer((UUID) rs.getObject(1), rs.getString(2),
                        rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10), rs.getString(11));
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

        return foundCustomer;
    }
}
