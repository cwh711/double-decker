package double_decker.model;

/**
 * A representation of a playing-card.
 * Contains distinguishing information of cardback,
 * suit, and face value.
 */
public class Card {

    private final Cardback cardback;
    private final Suit suit;
    private final Value value;

    public Card(Cardback cardback, Suit suit, Value value) {
        this.cardback = cardback;
        this.suit = suit;
        this.value = value;
    }

    public Cardback getCardback() {
        return cardback;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }
}
