package dao;

import resource.Payment;
import resource.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

public class UpdatePaymentDatabase {

    private static final String STATEMENT = "UPDATE payment SET method=?::paymentmethod, totalamount=?, complete=? WHERE paymentid=? RETURNING *;";

    private final Connection con;

    Payment payment;

    public UpdatePaymentDatabase(final Connection con, final Payment b) {
        this.con = con;
        this.payment = b;
    }

    public Payment updatePayment() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Payment updatedP = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, payment.getPaymentMethod().getName());
            pstmt.setDouble(2, payment.getTotalAmount());
            pstmt.setBoolean(3, payment.isComplete());
            pstmt.setObject(4, payment.getPaymentID());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                updatedP = new Payment(UUID.fromString(rs.getString(1)), rs.getDouble(2),
                        PaymentMethod.valueOf(rs.getString(3).toUpperCase(Locale.ROOT).replace(' ', '_')),
                        rs.getDate(4), rs.getBoolean(5), rs.getString(6));
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

        return updatedP;
    }
}


