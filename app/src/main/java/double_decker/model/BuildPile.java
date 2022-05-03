package double_decker.model;

public class BuildPile extends Pile{
    private final Suit targetSuit;

    /**
     * true -> building UP
     * false -> building DOWN
     */
    private final boolean direction;

    /**
     * Initialize a new Build Pile
     *
     * @param suit the Suit of cards this pile will accept
     * @param direction <code>true</code> -> building UP (A-K), <code>false</code> -> building DOWN (K-A)
     */
    public BuildPile(Suit suit, boolean direction) {
        super();
        targetSuit = suit;
        this.direction = direction;
    }

    public Suit getTargetSuit() {
        return targetSuit;
    }

    public boolean getDirection() {
        return direction;
    }

    @Override
    public GrabbedCard popCard() {
        return null;
    }

    @Override
    public void placeCard(Card c) {}

    @Override
    public boolean pushCard(Card c) {
        if (targetSuit.equals(c.getSuit())
            && c.getValue().equals(nextValue())) {
            return super.pushCard(c);
        } else {
            return false;
        }
    }

    protected Value nextValue() {
        if (cards.isEmpty()) {
            return direction ? Value.ACE : Value.KING;
        } else {
            Card topCard = cards.peek();
            int thisNum = topCard.getValue().getNum();
            int nextNum = direction ? thisNum + 1 : thisNum - 1;
            return Value.fromInt(nextNum);
        }
    }
}
