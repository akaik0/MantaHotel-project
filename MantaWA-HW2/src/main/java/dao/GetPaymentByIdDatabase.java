package dao;

import resource.Payment;
import resource.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

public class GetPaymentByIdDatabase {

    private static final String STATEMENT = "SELECT * FROM payment WHERE paymentid=?;";

    private final Connection con;

    UUID payment;

    public GetPaymentByIdDatabase(final Connection con, final UUID p) {
        this.con = con;
        this.payment = p;
    }

    public Payment getPaymentById() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Payment searchedPayment = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, payment);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                searchedPayment = new Payment(UUID.fromString(rs.getString(1)), rs.getDouble(2),
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

        return searchedPayment;
    }
}


