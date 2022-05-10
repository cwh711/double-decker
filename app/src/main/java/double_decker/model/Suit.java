package double_decker.model;

public enum Suit {
    HEARTS("hearts"),
    SPADES("spades"),
    DIAMONDS("diamonds"),
    CLUBS("clubs");

    private final String display;

    Suit(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
