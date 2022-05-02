package double_decker;

import double_decker.model.GameModel;
import double_decker.model.MainPile;
import double_decker.model.Suit;
import double_decker.model.Value;
import double_decker.view.DrawPilePanel;
import double_decker.view.MainPilePanel;
import double_decker.view.ObjectivePilePanel;

import javax.swing.*;
import java.awt.*;

public class Application {

    GameModel model;

    public static final Dimension CARD_SIZE = new Dimension(85,121);
    Application(String[] args) {
        model = new GameModel();
        model.newGame();
    }

    public void show() {
        JFrame frame = new JFrame("Double-decker Solitaire");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        JPanel root = new JPanel();
        GridBagLayout gbLayout = new GridBagLayout();
        root.setLayout(gbLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;

        initObjectivePilesPanels(root, gbLayout, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        JSeparator sep1 = new JSeparator();
        gbLayout.setConstraints(sep1, c);
        root.add(sep1);

        initMainPilesPanels(root, gbLayout, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        JSeparator sep2 = new JSeparator();
        gbLayout.setConstraints(sep2, c);
        root.add(sep2);

        initHandPanel(root, gbLayout, c);

        frame.setContentPane(root);
        frame.pack();
        frame.setVisible(true);
    }

    private void initObjectivePilesPanels(JPanel root, GridBagLayout layout, GridBagConstraints c) {

        c.gridwidth = 2;
        ObjectivePilePanel panel = new ObjectivePilePanel(model.buildUpPiles.get(Suit.HEARTS));
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(model.buildUpPiles.get(Suit.SPADES));
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(model.buildUpPiles.get(Suit.DIAMONDS));
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(model.buildUpPiles.get(Suit.CLUBS));
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(model.buildDownPiles.get(Suit.CLUBS));
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(model.buildDownPiles.get(Suit.DIAMONDS));
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(model.buildDownPiles.get(Suit.SPADES));
        layout.setConstraints(panel, c);
        root.add(panel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        panel = new ObjectivePilePanel(model.buildDownPiles.get(Suit.HEARTS));
        layout.setConstraints(panel, c);
        root.add(panel);
    }

    private void initMainPilesPanels(JPanel root, GridBagLayout layout, GridBagConstraints c) {
        c.gridwidth = 1;
        c.weightx = 0.5;
        JPanel spacer = new JPanel();
        layout.setConstraints(spacer, c);
        root.add(spacer);

        for (MainPile mp : model.numberedPiles) {
            c.gridwidth = 2;
            c.weightx = 1;
            MainPilePanel panel = new MainPilePanel(mp, mp.getPileValue());
            layout.setConstraints(panel, c);
            root.add(panel);
            if (Value.TEN.equals(mp.getPileValue())) {
                DrawPilePanel drawPilePanel = new DrawPilePanel(model.drawPile);
                layout.setConstraints(drawPilePanel, c);
                root.add(drawPilePanel);
            } else if (Value.SEVEN.equals(mp.getPileValue())) {
                spacer = new JPanel();
                c.gridwidth = GridBagConstraints.REMAINDER;
                layout.setConstraints(spacer, c);
                root.add(spacer);

                spacer = new JPanel();
                c.gridwidth = 1;
                c.weightx = 0.5;
                layout.setConstraints(spacer, c);
                root.add(spacer);
            }
        }
        spacer = new JPanel();
        c.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(spacer, c);
        root.add(spacer);
    }

    private void initHandPanel(JPanel root, GridBagLayout layout, GridBagConstraints c) {
        JPanel handPanel = new JPanel();
        JButton button = new JButton();
        button.setText("Draw size: " + model.drawPile.count());
        handPanel.add(button);
        c.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(handPanel, c);
        root.add(handPanel);
    }

    public static void main(final String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> new Application(args).show());
    }
}
