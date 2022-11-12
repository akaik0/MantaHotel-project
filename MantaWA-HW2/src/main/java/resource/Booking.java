package resource;

import com.fasterxml.jackson.core.JsonToken;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.UUID;


public class Booking extends Resource{

    private final UUID bookingid;
    private UUID personid;
    private Timestamp checkin;
    private Timestamp checkout;
    private UUID paymentid;
    private Date date;
    private String requests;

    public Booking(UUID bookingid){
        this.bookingid = bookingid;
    }

    public Booking(UUID bookingid, UUID personid, Timestamp checkin, Timestamp checkout, UUID paymentid, String requests){
        this.bookingid = bookingid;
        this.personid = personid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.paymentid = paymentid;
        this.date = Date.valueOf(java.time.LocalDate.now());
        this.requests = requests;
    }

    public Booking(UUID bookingid, UUID personid, Timestamp checkin, Timestamp checkout, UUID paymentid, Date date, String requests){
        this.bookingid = bookingid;
        this.personid = personid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.paymentid = paymentid;
        this.date = date;
        this.requests = requests;
    }

    public UUID getBookingid() { return bookingid; }

    public UUID getPersonid() { return personid; }

    public Timestamp getCheckin() { return checkin; }

    public Timestamp getCheckout() { return checkout; }

    public UUID getPaymentid() { return paymentid; }

    public Date getDate() { return date; }

    public String getRequests() { return requests; }

    public static Booking fromJSON(final InputStream in) throws IOException {

        // the fields read from JSON
        UUID jbookingid = UUID.randomUUID();
        UUID jpersonid = null;
        Timestamp jcheckin = null;
        Timestamp jcheckout = null;
        UUID jpaymentid = null;
        Date jdate = null;
        String jrequests = null;

        final JsonParser jp = JSON_FACTORY.createParser(in);

        // while we are not on the start of an element or the element is not
        // a token element, advance to the next element (if any)
        while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !"booking".equals(jp.getCurrentName())) {

            // there are no more events
            if (jp.nextToken() == null) {
                throw new IOException("Unable to parse JSON: no booking object found.");
            }
        }

        while (jp.nextToken() != JsonToken.END_OBJECT) {

            if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                switch (jp.getCurrentName()) {
                    case "bookingId":
                        jp.nextToken();
                        jbookingid = UUID.fromString(jp.getText());
                        break;
                    case "personId":
                        jp.nextToken();
                        jpersonid = UUID.fromString(jp.getText());
                        break;
                    case "checkin":
                        jp.nextToken();
                        jcheckin = Timestamp.valueOf(jp.getText());
                        break;
                    case "checkout":
                        jp.nextToken();
                        jcheckout = Timestamp.valueOf(jp.getText());
                        break;
                    case "paymentId":
                        jp.nextToken();
                        jpaymentid = UUID.fromString(jp.getText());
                        break;
                    case "date":
                        jp.nextToken();
                        jdate = Date.valueOf(jp.getText());
                        break;
                    case "requests":
                        jp.nextToken();
                        jrequests = jp.getText();
                        break;
                }
            }
        }

        return new Booking(jbookingid, jpersonid, jcheckin, jcheckout, jpaymentid, jdate, jrequests);
    }

    @Override
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("booking");

        jg.writeStartObject();

        jg.writeStringField("bookingId", String.valueOf(bookingid));

        jg.writeStringField("personId", String.valueOf(personid));

        jg.writeStringField("checkin", String.valueOf(checkin));

        jg.writeStringField("checkout", String.valueOf(checkout));

        jg.writeStringField("paymentId", String.valueOf(paymentid));

        jg.writeStringField("date", String.valueOf(date));

        jg.writeStringField("requests", requests);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

}


