package double_decker.model;

public enum Value {

    ACE(1,"ace"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "jack"),
    QUEEN(12, "queen"),
    KING(13, "king");

    private final int num;
    private final String display;

    Value(int num, String display) {
        this.num = num;
        this.display = display;
    }

    public int getNum() {
        return num;
    }

    public String getDisplay() {
        return display;
    }

    public static Value fromInt(int n) {
        for (Value v : values()) {
            if (v.num == n) {
                return v;
            }
        }
        return null;
    }
}
