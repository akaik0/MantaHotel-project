package resource;

public enum PaymentMethod {
    VISA("Visa"),
    MASTERCARD("MasterCard"),
    MAESTRO("Maestro"),
    AMERICAN_EXPRESS("American Express"),
    PAYPAL("PayPal"),
    CASH("Cash");

    private final String name;

    PaymentMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

