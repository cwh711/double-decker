package double_decker.view.listener;

import double_decker.model.Card;
import double_decker.model.GameModel;
import double_decker.model.MainPile;
import double_decker.view.DrawPilePanel;
import double_decker.view.HandPanel;
import double_decker.view.RootPanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class DrawPileMouseHandler extends MouseInputAdapter {

    RootPanel root;
    GameModel model;

    public DrawPileMouseHandler(RootPanel root, GameModel model) {
        this.root = root;
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (e.getComponent() instanceof DrawPilePanel) {
                DrawPilePanel drawPanel = root.getDrawPilePanel();
                HandPanel handPanel = root.getHandPanel();
                MainPile prevPile = handPanel.getHand().getFromPile();
                if (model.drawPile.count() > 0) {
                    handPanel.putDown();

                    Card drawnCard = model.drawPile.popCard();
                    MainPile pickedUpPile = model.getMainPileForValue(drawnCard.getValue());
                    pickedUpPile.addLast(drawnCard);
                    model.hand.pickUpFromPile(pickedUpPile);

                    handPanel.updateCards();
                    drawPanel.updateImage();
                    root.getMainPilePanel(drawnCard.getValue()).updateImage();
                    if (prevPile != null) {
                        root.getMainPilePanel(prevPile.getPileValue()).updateImage();
                    }
                    root.displayLog("Drew " + drawnCard.getValue().getDisplay() + " of " + drawnCard.getSuit().getDisplay() + ". " +
                            model.drawPile.count() + " cards left.");
                }
            }
        }
    }
}
