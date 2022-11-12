package resource;

public enum RoomType {

    SINGLE("Single"),
    DOUBLE("Double"),
    TRIPLE("Triple"),
    QUAD("Quad"),
    TWIN("Twin"),
    SUITE("Suite");

    private final String name;

    RoomType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
