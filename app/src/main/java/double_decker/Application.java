package double_decker;

import double_decker.model.*;
import double_decker.view.*;
import double_decker.view.listener.DrawPileMouseHandler;
import double_decker.view.listener.MenuActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Application {

    GameModel model;
    JFrame window;

    public static final Dimension CARD_SIZE = new Dimension(85,121);

    Application(String[] args) {
        model = new GameModel();
        model.newGame();
    }

    public void show() {
        JFrame frame = new JFrame("Double-decker Solitaire");
        this.window = frame;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 1000));

        RootPanel root = new RootPanel(model);
        GridBagLayout gbLayout = new GridBagLayout();
        root.setLayout(gbLayout);
        GridBagConstraints c = new GridBagConstraints();

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

        initMenu(root);

        JPanel statusBar = new JPanel();
        statusBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        statusBar.setLayout(new BorderLayout());
        JLabel logLabel = new JLabel("Started new game...");
        statusBar.add(logLabel, BorderLayout.WEST);
        JLabel scoreLabel = new JLabel();
        statusBar.add(scoreLabel, BorderLayout.EAST);
        root.setLabels(logLabel, scoreLabel);
        root.updateScore();

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(root, BorderLayout.NORTH);
        containerPanel.add(statusBar, BorderLayout.SOUTH);

        frame.setContentPane(containerPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void initMenu(RootPanel root) {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu optionMenu = new JMenu("Option");
        menuBar.add(gameMenu);
        menuBar.add(optionMenu);

        JMenuItem newGameMenuItem = new JMenuItem(Constants.MENU_TEXT_NEW_GAME, KeyEvent.VK_N);
        newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        gameMenu.add(newGameMenuItem);

        JMenuItem hintMenuItem = new JMenuItem(Constants.MENU_TEXT_HINT, KeyEvent.VK_H);
        hintMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        gameMenu.add(hintMenuItem);

        JCheckBoxMenuItem scoreOptionMenuItem = new JCheckBoxMenuItem("Enable Scoring?", model.scoreEnabled);
        optionMenu.add(scoreOptionMenuItem);

        MenuActionHandler handler = new MenuActionHandler(model, root);
        newGameMenuItem.addActionListener(handler);
        hintMenuItem.addActionListener(handler);
        scoreOptionMenuItem.addActionListener(handler);


        window.setJMenuBar(menuBar);
    }

    private void initObjectivePilesPanels(RootPanel root, GridBagLayout layout, GridBagConstraints c) {

        c.gridwidth = 1;
        ObjectivePilePanel panel = new ObjectivePilePanel(root, model, Suit.HEARTS, true);
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(root, model, Suit.SPADES, true);
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(root, model, Suit.DIAMONDS, true);
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(root, model, Suit.CLUBS,true);
        layout.setConstraints(panel, c);
        root.add(panel);

        JPanel keyPanel = new ImagePanel("key.png", CARD_SIZE);
        layout.setConstraints(keyPanel, c);
        root.add(keyPanel);

        panel = new ObjectivePilePanel(root, model, Suit.CLUBS, false);
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(root, model, Suit.DIAMONDS, false);
        layout.setConstraints(panel, c);
        root.add(panel);

        panel = new ObjectivePilePanel(root, model, Suit.SPADES, false);
        layout.setConstraints(panel, c);
        root.add(panel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        panel = new ObjectivePilePanel(root, model, Suit.HEARTS, false);
        layout.setConstraints(panel, c);
        root.add(panel);
    }

    private JPanel makeSpacer() {
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(CARD_SIZE);
        spacer.setMaximumSize(CARD_SIZE);
        spacer.setMinimumSize(CARD_SIZE);
        return spacer;
    }

    private void initMainPilesPanels(RootPanel root, GridBagLayout layout, GridBagConstraints c) {
        c.gridwidth = 1;
        JPanel spacer = makeSpacer();
        layout.setConstraints(spacer, c);
        root.add(spacer);

        for (MainPile mp : model.numberedPiles) {
            c.gridwidth = 1;
            MainPilePanel panel = new MainPilePanel(mp, mp.getPileValue());
            layout.setConstraints(panel, c);
            root.add(panel);
            if (Value.TEN.equals(mp.getPileValue())) {
                DrawPilePanel drawPilePanel = new DrawPilePanel(model.drawPile);
                drawPilePanel.addMouseListener(new DrawPileMouseHandler(root, model));
                layout.setConstraints(drawPilePanel, c);
                root.add(drawPilePanel);
            } else if (Value.SEVEN.equals(mp.getPileValue())) {
                spacer = makeSpacer();
                c.gridwidth = GridBagConstraints.REMAINDER;
                layout.setConstraints(spacer, c);
                root.add(spacer);

                spacer = makeSpacer();
                c.gridwidth = 1;
                layout.setConstraints(spacer, c);
                root.add(spacer);
            }
        }
        spacer = makeSpacer();
        c.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(spacer, c);
        root.add(spacer);
    }

    private void initHandPanel(RootPanel root, GridBagLayout layout, GridBagConstraints c) {
        HandPanel handPanel = new HandPanel(model);
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
