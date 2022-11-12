package dao;

import resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

public class ListCustomersByNameDatabase {

    private static final String STATEMENT = "SELECT * FROM customer WHERE customer.name=? AND customer.surname=?;";

    private final Connection con;
    String name;
    String surname;

    public ListCustomersByNameDatabase(final Connection con, final String name, final String surname) {
        this.con = con;
        this.name = name;
        this.surname = surname;
    }

    public List <Customer> listCustomersByName() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Customer> customers = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, name);
            pstmt.setString(2, surname);

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




