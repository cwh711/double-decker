package double_decker.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class Pile {

    private final Deque<Card> cards;

    public Pile() {
        cards = new ArrayDeque<>();
    }

    public void placeCard(Card c) {
        cards.push(c);
    }

    public boolean pushCard(Card c) {
        cards.push(c);
        return true;
    }

    public GrabbedCard popCard() {
        return new GrabbedCard(cards.pop(), this);
    }

    public Card peekCard() {
        return cards.peek();
    }

    public void clear() {
        cards.clear();
    }

    public int count() {
        return cards.size();
    }
}
