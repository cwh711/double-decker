package double_decker.view;

import double_decker.Application;
import double_decker.model.Card;
import double_decker.model.MainPile;
import double_decker.model.Value;
import double_decker.view.listener.MainPileDragHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.io.IOException;
import java.net.URL;

public class MainPilePanel extends JPanel {
    private final MainPile pile;
    private final Value pileValue;
    private Image image;
    private DragGestureRecognizer dgr;
    private MainPileDragHandler dragHandler;

    public MainPilePanel(MainPile pile, Value value) {
        super();
        this.pile = pile;
        this.pileValue = value;
        updateImage();
        setPreferredSize(Application.CARD_SIZE);
        setMinimumSize(Application.CARD_SIZE);
        setMaximumSize(Application.CARD_SIZE);
        setOpaque(true);
    }

    public MainPile getPile() {
        return pile;
    }

    public Value getPileValue() {
        return pileValue;
    }

    public void updateImage() {
        Card topCard = pile.peekCard();
        String fileName;
        if (topCard != null) {
            fileName = topCard.getFrontImageFileName();
        } else {
            fileName = "empty_" + pileValue.getDisplay() + ".png";
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (dgr == null) {
            dragHandler = new MainPileDragHandler(this);
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
