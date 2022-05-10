package double_decker.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();
    private MainPile fromPile;

    public void pickUpFromPile(MainPile pile) {
        fromPile = pile;
        cards.addAll(pile.cards);
        pile.cards.clear();
    }

    public void putDownPile() {
        if (fromPile != null) {
            fromPile.cards.addAll(cards);
        }
        cards.clear();
        fromPile = null;
    }

    public List<Card> getCards() {
        return cards;
    }

    public MainPile getFromPile() {
        return fromPile;
    }
}
