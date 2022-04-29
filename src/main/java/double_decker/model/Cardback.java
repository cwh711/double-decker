package double_decker.model;

public enum Cardback {
    RED("red"),
    BLUE("blue");

    private final String display;

    Cardback(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
