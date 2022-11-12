package dao;

import resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListCustomersDatabase {
    private static final String STATEMENT = "SELECT * FROM customer;";

    private final Connection con;

    public ListCustomersDatabase(final Connection con) {
        this.con = con;
    }

    public List<Customer> listCustomers() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Customer> customers = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(UUID.fromString(rs.getString(1)),
                        rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getDate(10),
                        rs.getString(11)));
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

        return customers;
    }

}

