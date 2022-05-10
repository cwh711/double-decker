package double_decker.view;

import double_decker.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(String imageFileName, Dimension size) {
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        try {
            URL imgURL = getClass().getClassLoader().getResource(imageFileName);
            if (imgURL != null) {
                image = ImageIO.read(imgURL);
            } else {
                System.out.println("Image <" + imageFileName + "> not found.");
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
}
