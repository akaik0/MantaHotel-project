package resource;

import java.sql.Date;
import java.util.UUID;

public class Payment {

    private final UUID paymentid;
    private PaymentMethod paymentMethod;
    private double totalAmount;
    private Date date;
    private boolean complete;
    private String email;

    public Payment(UUID paymentid, double tot, PaymentMethod pm, Date date, Boolean b, String email) {
        this.paymentid = paymentid;
        this.paymentMethod = pm;
        this.totalAmount = tot;
        this.date = date;
        this.complete = b;
        this.email = email;
    }

    public Payment(UUID paymentid, PaymentMethod pm, String email) {
        this.paymentid = paymentid;
        this.paymentMethod = pm;
        this.date = Date.valueOf(java.time.LocalDate.now());
        this.complete = false;
        this.email = email;
    }

    public Payment(UUID paymentid, PaymentMethod pm, double tot, String email) {
        this.paymentid = paymentid;
        this.paymentMethod = pm;
        this.totalAmount = tot;
        this.date = Date.valueOf(java.time.LocalDate.now());
        this.complete = false;
        this.email = email;
    }

    public Payment(UUID paymentid, PaymentMethod pm, double tot) {
        this.paymentid = paymentid;
        this.paymentMethod = pm;
        this.totalAmount = tot;
        this.date = Date.valueOf(java.time.LocalDate.now());
        this.complete = false;
    }

    public UUID getPaymentID() {
        return paymentid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getTotalAmount() { return totalAmount; }

    public Date getDate() {
        return date;
    }

    public boolean isComplete() {
        return complete;
    }

    public String getEmail() {
        return email;
    }

}