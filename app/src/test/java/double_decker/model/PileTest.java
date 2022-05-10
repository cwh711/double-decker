package double_decker.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PileTest {

    Pile objectUnderTest = new Pile();

    @BeforeEach
    void setUp() {
        objectUnderTest.cards.push(new Card(Cardback.BLUE, Suit.HEARTS, Value.ACE));
    }

    @AfterEach
    void tearDown() {
        objectUnderTest.cards.clear();
    }

    @Test
    void placeCard() {
        Card testCard = new Card(Cardback.RED, Suit.CLUBS, Value.SEVEN);
        objectUnderTest.placeCard(testCard);
        assertEquals(objectUnderTest.cards.size(), 2);
        assertEquals(objectUnderTest.cards.peekFirst(), testCard);
    }

    @Test
    void pushCard() {
        Card testCard = new Card(Cardback.RED, Suit.CLUBS, Value.SEVEN);
        assertTrue(objectUnderTest.pushCard(testCard));
        assertEquals(2, objectUnderTest.cards.size());
        assertEquals(testCard, objectUnderTest.cards.peekFirst());
    }

    @Test
    void popCard() {
        Card expectedCard = new Card(Cardback.BLUE, Suit.HEARTS, Value.ACE);
        Card actual = objectUnderTest.popCard();
        assertEquals(expectedCard, actual);
        assertEquals(0, objectUnderTest.cards.size());
    }

    @Test
    void peekCard() {
        Card expectedCard = new Card(Cardback.BLUE, Suit.HEARTS, Value.ACE);
        assertEquals(expectedCard, objectUnderTest.peekCard());
        assertEquals(1, objectUnderTest.cards.size());
    }

    @Test
    void clear() {
        objectUnderTest.clear();
        assertEquals(0, objectUnderTest.cards.size());
    }

    @Test
    void count() {
        assertEquals(1, objectUnderTest.count());
        objectUnderTest.placeCard(new Card(Cardback.RED, Suit.SPADES, Value.TEN));
        assertEquals(2, objectUnderTest.count());
    }
}