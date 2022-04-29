package double_decker.view;

import double_decker.Application;
import double_decker.model.Card;
import double_decker.model.Pile;
import double_decker.model.Value;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class MainPilePanel extends JPanel {
    private final Pile pile;
    private final Value pileValue;
    private Image image;

    public MainPilePanel(Pile pile, Value value) {
        super();
        this.pile = pile;
        this.pileValue = value;
        updateImage();
        setPreferredSize(Application.CARD_SIZE);
        setMinimumSize(Application.CARD_SIZE);
        setMaximumSize(Application.CARD_SIZE);
        setOpaque(true);
    }

    public Pile getPile() {
        return pile;
    }

    public Value getPileValue() {
        return pileValue;
    }

    private void updateImage() {
        Card topCard = pile.peekCard();
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
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
