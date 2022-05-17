package double_decker.view.listener;

import double_decker.model.Card;
import double_decker.model.Constants;
import double_decker.model.GameModel;
import double_decker.model.MainPile;
import double_decker.model.ObjectivePile;
import double_decker.model.Suit;
import double_decker.model.Value;
import double_decker.view.HandPanel;
import double_decker.view.RootPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class MenuActionHandler implements ActionListener {

    private final GameModel model;

    private final RootPanel root;

    public MenuActionHandler(GameModel model, RootPanel root) {
        this.model = model;
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        if (Constants.MENU_TEXT_NEW_GAME.equals(source.getText())) {
            model.newGame();
            root.refresh();
            root.repaint();
        } else if (Constants.MENU_TEXT_HINT.equals(source.getText())) {
            doHint();
        }
    }

    private void doHint() {
        JPanel hintPanel = null;
        List<ObjectivePile> pilesToCheck = new ArrayList<>(model.buildUpPiles.values());
        pilesToCheck.addAll(model.buildDownPiles.values());
        for (ObjectivePile up : pilesToCheck) {
            Suit wantedSuit = up.getTargetSuit();
            Value wantedValue = up.nextValue();
            //Check numbered piles
            for (MainPile main : model.numberedPiles) {
                Card topCard = main.peekCard();
                if (topCard != null && topCard.getSuit() == wantedSuit
                        && topCard.getValue() == wantedValue) {
                    hintPanel = root.getMainPilePanel(main.getPileValue());
                    root.displayLog("Hint says: " + main.getPileValue().getDisplay() + " pile.");
                    break;
                }
            }
            if (hintPanel != null) {
                break;
            }
            //check hand
            for (int i = 0; i < model.hand.getCards().size(); i++) {
                Card thisCard = model.hand.getCards().get(i);
                if (thisCard.getSuit() == wantedSuit && thisCard.getValue() == wantedValue) {
                    HandPanel handPanel = root.getHandPanel();
                    int panelIndex = handPanel.getComponentCount() - i - 2;
                    hintPanel = (JPanel) handPanel.getComponent(panelIndex);
                    root.displayLog("Hint says card #" + (panelIndex + 1) + " in hand.");
                    break;
                }
            }
            if (hintPanel != null) {
                break;
            }
        }
        if (hintPanel == null) {
            hintPanel = root.getDrawPilePanel();
            root.displayLog("Hint says draw pile.");
        }
        hintPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        hintPanel.setBorder(null);
    }
}
