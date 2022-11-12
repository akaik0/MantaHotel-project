package dao;

import resource.Payment;
import resource.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

public class ListPaymentsByCustomerDatabase {

    private static final String STATEMENT = "SELECT payment.paymentid, payment.totalmount, payment.method, payment.date, " +
            "payment.complete, payment.hoteluser FROM payment JOIN booking ON payment.paymentid = booking.paymentid " +
            "WHERE booking.personid=?;";

    private final Connection con;
    UUID customer;

    public ListPaymentsByCustomerDatabase(final Connection con, final UUID c) {
        this.con = con;
        this.customer = c;
    }

    public List <Payment> listPaymentsByCustomer() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Payment> foundPayments = new ArrayList<Payment>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, customer);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                foundPayments.add(new Payment(UUID.fromString(rs.getString(1)), rs.getDouble(2),
                        PaymentMethod.valueOf(rs.getString(3).toUpperCase(Locale.ROOT).replace(' ', '_')),
                        rs.getDate(4), rs.getBoolean(5), rs.getString(6)));
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

        return foundPayments;
    }
}



