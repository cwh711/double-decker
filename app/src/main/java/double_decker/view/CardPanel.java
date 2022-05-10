package double_decker.view;

import double_decker.Application;
import double_decker.model.Card;
import double_decker.view.listener.HandDragHandler;
import double_decker.view.listener.MainPileDragHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class CardPanel extends JPanel {
    private final Card card;
    private BufferedImage image;
    private DragGestureRecognizer dgr;
    private HandDragHandler dragHandler;

    public CardPanel(Card c) {
        this.card = c;
        setPreferredSize(Application.CARD_SIZE);
        setMinimumSize(Application.CARD_SIZE);
        setMaximumSize(Application.CARD_SIZE);


        setImage();
    }

    public Card getCard() {
        return card;
    }

    private void setImage() {
        String fileName = card.getFrontImageFileName();
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (dgr == null) {
            dragHandler = new HandDragHandler((HandPanel) getParent(), this);
            dgr = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
                    this,
                    DnDConstants.ACTION_MOVE,
                    dragHandler);
        }
    }

    @Override
    public void removeNotify() {
        if (dgr != null) {
            dgr.removeDragGestureListener(dragHandler);
            dragHandler = null;
        }
        dgr = null;
        super.removeNotify();
    }
}
