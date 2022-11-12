package resource;

public class Room {

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
}
