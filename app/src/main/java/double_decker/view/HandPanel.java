package double_decker.view;

import double_decker.Application;
import double_decker.model.Card;
import double_decker.model.GameModel;
import double_decker.model.Hand;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandPanel extends JPanel {
    private final Hand hand;

    private final JPanel placeholder = new JPanel();

    public HandPanel(GameModel model) {
        this.hand = model.hand;
        placeholder.setPreferredSize(Application.CARD_SIZE);
        placeholder.setMinimumSize(Application.CARD_SIZE);
        placeholder.setMaximumSize(Application.CARD_SIZE);
        add(placeholder);
    }

    public Hand getHand() {
        return hand;
    }

    public void putDown() {
        hand.putDownPile();
    }

    public void returnCard(int index, Card c) {
        hand.getCards().add(index, c);
    }

    public int removeCard(Card c) {
        int index = hand.getCards().indexOf(c);
        if (index >= 0) {
            hand.getCards().remove(index);
            int reverseIndex = getComponentCount() - 2 - index;
            getComponent(reverseIndex).setVisible(false);
            placeholder.setVisible(hand.getCards().isEmpty());
        }
        return index;
    }

    public void updateCards() {
        removeAll();
        List<Card> cardList = new ArrayList<>(hand.getCards());
        Collections.reverse(cardList);
        for (Card c : cardList) {
            add(new CardPanel(c));
        }
        add(placeholder);
        placeholder.setVisible(cardList.isEmpty());
        getParent().validate();
    }
}
