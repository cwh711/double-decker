package double_decker.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * A representation of a playing-card.
 * Contains distinguishing information of cardback,
 * suit, and face value.
 */
public class Card implements Serializable {

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

    public String getFrontImageFileName() {
        return suit.getDisplay() + "_" + value.getDisplay() + ".png";
    }

    public String getBackImageFileName() {
        return "backs/" + cardback.getDisplay() + ".png";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardback == card.cardback && suit == card.suit && value == card.value;
    }

    public boolean equalsIgnoreCardback(Card other) {
        return (this.suit == other.suit && this.value == other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardback, suit, value);
    }
}
