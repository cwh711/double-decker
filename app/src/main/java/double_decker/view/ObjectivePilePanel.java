package double_decker.view;

import double_decker.Application;
import double_decker.model.ObjectivePile;
import double_decker.model.Card;
import double_decker.model.GameModel;
import double_decker.model.Suit;
import double_decker.view.listener.DropHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class ObjectivePilePanel extends JPanel {

    private final ObjectivePile pile;
    private final GameModel model;
    private final RootPanel root;
    private BufferedImage image;
    private DropHandler dropHandler;
    private DropTarget dropTarget;

    public ObjectivePilePanel(RootPanel root, GameModel model, Suit suit, boolean direction) {
        this.root = root;
        this.model = model;

        Map<Suit, ObjectivePile> pileList = direction ? model.buildUpPiles : model.buildDownPiles;
        this.pile =  pileList.get(suit);

        setPreferredSize(Application.CARD_SIZE);
        setMinimumSize(Application.CARD_SIZE);
        setMaximumSize(Application.CARD_SIZE);
        updateImage();
        setOpaque(true);
    }

    public void updateImage() {
        Card topCard = pile.peekCard();
        String fileName;
        if (topCard != null) {
            fileName = topCard.getFrontImageFileName();
        } else {
            fileName = getSuit().getDisplay() + "_empty.png";
        }
        try {
            URL imgURL = getClass().getClassLoader().getResource(fileName);
            if (imgURL != null) {
                image = ImageIO.read(imgURL);
            } else {
                System.out.println("Image <" + fileName + "> not found.");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        repaint();
    }

    public boolean addCardToPile(Card c) {
        return pile.pushCard(c);
    }

    public Suit getSuit() {
        return pile.getTargetSuit();
    }

    public boolean getDirection() {
        return pile.getDirection();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        dropHandler = new DropHandler(root);
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        dropTarget.removeDropTargetListener(dropHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
