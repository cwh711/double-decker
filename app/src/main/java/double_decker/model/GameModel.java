package double_decker.model;

import java.util.*;

public class GameModel {
    public final List<MainPile> numberedPiles = initNumberedPiles();
    public final Pile drawPile = new Pile();
    public Map<Suit,BuildPile> buildUpPiles = initBuildPiles(true);
    public Map<Suit,BuildPile> buildDownPiles = initBuildPiles(false);

    public GameModel() {
    }

    public void newGame() {
        resetGame();
        List<Card> allCards = generateDecks();
        Collections.shuffle(allCards);
        deal(allCards);
    }

    public void resetGame() {
        for (Pile np : numberedPiles) {
            np.clear();
        }
        drawPile.clear();
        for (BuildPile bup : buildUpPiles.values()) {
            bup.clear();
        }
        for (BuildPile bdp : buildDownPiles.values()) {
            bdp.clear();
        }
    }

    private List<Card> generateDecks() {
        List<Card> allCards = new ArrayList<>();
        for (Cardback c : Cardback.values()) {
            for (Suit s : Suit.values()) {
                for (Value v : Value.values()) {
                    allCards.add(new Card(c, s, v));
                }
            }
        }
        return allCards;
    }

    private static List<MainPile> initNumberedPiles() {
        List<MainPile> temp = new ArrayList<>(13);
        for (Value v : Value.values()) {
            temp.add(new MainPile(v));
        }
        return Collections.unmodifiableList(temp);
    }

    private static Map<Suit,BuildPile> initBuildPiles(boolean direction) {
        Map<Suit,BuildPile> temp = new HashMap<>();
        for (Suit s : Suit.values()) {
            temp.put(s, new BuildPile(s, direction));
        }
        return Collections.unmodifiableMap(temp);
    }

    private void deal(List<Card> deck) {
        int currentPile = 0;
        while (!deck.isEmpty()) {
            Card thisCard = dealToPile(deck, numberedPiles.get(currentPile));
            if (thisCard.getValue() == Value.ACE) {
                dealToDrawPile(deck, 2);
            }
            //numbered piles are zero-indexed, so off-by-one in comparison
            if (thisCard.getValue().getNum() - 1 == currentPile) {
                dealToDrawPile(deck, 1);
            }

            if (currentPile == 9 // Tens pile, off-by-one
                    || currentPile == 12) { // Kings pile, off-by-one
                dealToDrawPile(deck, 1);
            }
            currentPile = (currentPile + 1) % 13;
        }
    }

    private Card dealToPile(List<Card> deck, Pile pile) {
        Card c = null;
        if (!deck.isEmpty()) {
            c = deck.remove(0);
            pile.placeCard(c);
        }
        return c;
    }

    private void dealToDrawPile(List<Card> deck, int count) {
        for (int i = 0; i < count; i++) {
            dealToPile(deck, drawPile);
        }
    }
}
