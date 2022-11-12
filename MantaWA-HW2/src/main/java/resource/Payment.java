package resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class Payment extends Resource{

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

    @Override
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("payment");

        jg.writeStartObject();

        jg.writeStringField("paymentId", String.valueOf(paymentid));

        jg.writeStringField("paymentMethod", String.valueOf(paymentMethod));

        jg.writeStringField("totalAmount", String.valueOf(totalAmount));

        jg.writeStringField("date", String.valueOf(date));

        jg.writeStringField("complete", String.valueOf(complete));

        jg.writeStringField("email", String.valueOf(email));

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

}