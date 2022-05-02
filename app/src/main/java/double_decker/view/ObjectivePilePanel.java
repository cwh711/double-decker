package double_decker.view;

import double_decker.Application;
import double_decker.model.BuildPile;
import double_decker.model.Card;
import double_decker.model.Suit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ObjectivePilePanel extends JPanel {
    private final BuildPile pile;
    private BufferedImage image;

    public ObjectivePilePanel(BuildPile pile) {
        super();
        this.pile = pile;
        setTransferHandler(new PileTransferHandler());
        Color bgColor;
        Suit suit = pile.getTargetSuit();
        if (Suit.HEARTS.equals(suit) || Suit.DIAMONDS.equals(suit)) {
            bgColor = Color.PINK;
        } else {
            bgColor = Color.LIGHT_GRAY;
        }
        setBackground(bgColor);
        JLabel label = new JLabel(suit.getName() + (pile.getDirection() ? "-u" : "-d"));
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label);
        setPreferredSize(Application.CARD_SIZE);
        setMinimumSize(Application.CARD_SIZE);
        setMaximumSize(Application.CARD_SIZE);
        setOpaque(true);
    }

    private void updateImage() {
        Card topCard = pile.peekCard();
        if (topCard != null) {
            String fileName = topCard.getSuit().getName() + "_" + topCard.getValue().getDisplay() + ".png";
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
        repaint();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }


}
