package double_decker.model;

public class BuildPile extends Pile{
    private final Suit targetSuit;

    /**
     * true -> building UP
     * false -> building DOWN
     */
    private final boolean direction;

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
        if (targetSuit.equals(c.getSuit())) {
            return super.pushCard(c);
        } else {
            return false;
        }
    }
}
