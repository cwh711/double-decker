package double_decker.view;

import double_decker.Application;
import double_decker.model.Card;
import double_decker.model.Pile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class DrawPilePanel extends JPanel {
    private final Pile pile;
    private Image image;

    public DrawPilePanel(Pile pile) {
        super();
        this.pile = pile;
        updateImage();
        setPreferredSize(Application.CARD_SIZE);
        setMinimumSize(Application.CARD_SIZE);
        setMaximumSize(Application.CARD_SIZE);
        setOpaque(true);
    }

    public Pile getPile() {
        return pile;
    }

    @Override
    public Dimension getPreferredSize() {
        return Application.CARD_SIZE;
    }

    @Override
    public Dimension getMaximumSize() {
        return Application.CARD_SIZE;
    }

    @Override
    public Dimension getMinimumSize() {
        return Application.CARD_SIZE;
    }

    private void updateImage() {
        Card topCard = pile.peekCard();
        if (topCard != null) {
            String fileName = "backs/" + topCard.getCardback().getDisplay() + ".png";
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
