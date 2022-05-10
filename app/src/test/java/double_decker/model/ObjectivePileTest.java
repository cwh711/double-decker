package double_decker.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectivePileTest {

    ObjectivePile objectUnderTest = new ObjectivePile(Suit.SPADES, true);

    @BeforeEach
    void setUp() {
        objectUnderTest.cards.push(new Card(Cardback.RED, Suit.SPADES, Value.ACE));
        objectUnderTest.cards.push(new Card(Cardback.BLUE, Suit.SPADES, Value.TWO));
    }

    @AfterEach
    void tearDown() {
        objectUnderTest.cards.clear();
    }

    @Test
    void getTargetSuit() {
        assertEquals(Suit.SPADES, objectUnderTest.getTargetSuit());
    }

    @Test
    void getDirection() {
        assertTrue(objectUnderTest.getDirection());
    }

    @Test
    void popCard() {
        Card actual = objectUnderTest.popCard();
        assertNull(actual);
        assertEquals(2, objectUnderTest.cards.size());
    }

    @Test
    void placeCard() {
        Card expected = new Card(Cardback.BLUE, Suit.SPADES, Value.TWO);
        objectUnderTest.placeCard(new Card(Cardback.BLUE, Suit.SPADES, Value.THREE));
        assertEquals(2, objectUnderTest.cards.size());
        assertEquals(expected, objectUnderTest.cards.peek());
    }

    @Test
    void pushCard() {
        Card testCard1 = new Card(Cardback.BLUE, Suit.CLUBS, Value.THREE);
        Card testCard2 = new Card(Cardback.RED, Suit.SPADES, Value.ACE);
        Card testCard3 = new Card(Cardback.RED, Suit.SPADES, Value.THREE);

        assertFalse(objectUnderTest.pushCard(testCard1));
        assertEquals(2, objectUnderTest.cards.size());
        assertFalse(objectUnderTest.pushCard(testCard2));
        assertEquals(2, objectUnderTest.cards.size());
        assertTrue(objectUnderTest.pushCard(testCard3));
        assertEquals(3, objectUnderTest.cards.size());
    }

    @Test
    void nextValue() {
        //building up, currently showing 2
        assertEquals(Value.THREE, objectUnderTest.nextValue());

        //force King on top
        objectUnderTest.cards.push(new Card(Cardback.RED, Suit.SPADES, Value.KING));
        assertNull(objectUnderTest.nextValue());

        //empty pile
        objectUnderTest.cards.clear();
        assertEquals(Value.ACE, objectUnderTest.nextValue());

        //pile building down
        objectUnderTest = new ObjectivePile(Suit.SPADES, false);
        assertEquals(Value.KING, objectUnderTest.nextValue());

        objectUnderTest.cards.push(new Card(Cardback.RED, Suit.SPADES, Value.EIGHT));
        assertEquals(Value.SEVEN, objectUnderTest.nextValue());

        objectUnderTest.cards.push(new Card(Cardback.BLUE, Suit.SPADES, Value.ACE));
        assertNull(objectUnderTest.nextValue());
    }
}