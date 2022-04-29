package double_decker.model;

public class MainPile extends Pile {
    private final Value pileValue;

    public MainPile(Value pileValue) {
        super();
        this.pileValue = pileValue;
    }

    public Value getPileValue() {
        return pileValue;
    }
}
