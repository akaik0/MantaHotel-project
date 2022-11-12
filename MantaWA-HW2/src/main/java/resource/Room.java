package resource;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;

public class Room extends Resource{

    private final String roomNumber;
    private final int beds;
    private double price;
    private final RoomType roomType;
    private String description;

    public Room(final String roomNumber, final int beds, final double price, final RoomType roomType, final String description) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.price = price;
        this.roomType = roomType;
        this.description = description;
    }

    public Room(final String roomNumber, final int beds, final RoomType roomType) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("room");

        jg.writeStartObject();

        jg.writeStringField("roomNumber", String.valueOf(roomNumber));

        jg.writeStringField("beds", String.valueOf(beds));

        jg.writeStringField("price", String.valueOf(price));

        jg.writeStringField("roomType", String.valueOf(roomType));

        jg.writeStringField("description", String.valueOf(description));

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }
}
