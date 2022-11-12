package dao;

import resource.Customer;
import java.sql.*;
import java.util.UUID;

public class InsertCustomerDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING *;";
    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The user to be inserted
     */
    Customer customer;

    public InsertCustomerDatabase(final Connection con, final Customer c) {
        this.con = con;
        this.customer = c;
    }

    public Customer insertCustomer() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the created user
        Customer newCustomer = null;

        System.out.println(customer.getCustomerId() +"\n"+ customer.getName()+"\n"+customer.getSurname()  +"\n"
                +customer.getBirthDate() +"\n" +customer.getDocumentNumber() +"\n" +customer.getPhoneNumber()
                +"\n" +customer.getEmail() +"\n"+customer.getPassword() +"\n"+customer.getAddress()+"\n"+customer.getRegistrationDate() +"\n" + customer.getHotelUser());
        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, customer.getCustomerId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getSurname());
            pstmt.setDate(4, customer.getBirthDate());
            pstmt.setString(5, customer.getDocumentNumber());
            pstmt.setString(6, customer.getPhoneNumber());
            pstmt.setString(7, customer.getEmail());
            pstmt.setString(8, customer.getPassword());
            pstmt.setString(9, customer.getAddress());
            pstmt.setDate(10, customer.getRegistrationDate());
            pstmt.setString(11, customer.getHotelUser());

            System.out.println("pstmt: "+pstmt.toString());

            rs = pstmt.executeQuery();

            System.out.println("QUERY EXECUTED");


            if (rs.next()) {
                newCustomer = new Customer((UUID) rs.getObject(1), rs.getString(2),
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
        return newCustomer;
    }

}
