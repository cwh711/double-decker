package double_decker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainPileTest {

    MainPile objectUnderTest = new MainPile(Value.ACE);

    @Test
    void getPileValue() {
        assertEquals(Value.ACE, objectUnderTest.getPileValue());
    }

    @Test
    void addLast() {
        Card card1 = new Card(Cardback.BLUE, Suit.DIAMONDS, Value.EIGHT),
            card2 = new Card(Cardback.RED, Suit.SPADES, Value.THREE);
        objectUnderTest.cards.push(card1);

        objectUnderTest.addLast(card2);
        assertEquals(2, objectUnderTest.cards.size());
        assertEquals(card1, objectUnderTest.cards.peek());
    }
}