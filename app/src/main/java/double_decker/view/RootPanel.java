package double_decker.view;

import double_decker.model.GameModel;
import double_decker.model.Suit;
import double_decker.model.Value;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RootPanel extends JPanel {
    
    private final GameModel model;
    private final Map<Suit, ObjectivePilePanel> upObjectivePilePanels = new HashMap<>();
    private final Map<Suit, ObjectivePilePanel> downObjectivePilePanels = new HashMap<>();
    private final Map<Value, MainPilePanel> mainPilePanels = new HashMap<>();
    private DrawPilePanel drawPilePanel;
    private HandPanel handPanel;
    private JLabel logLabel;
    private JLabel scoreLabel;

    public RootPanel(GameModel model) {
        this.model = model;
    }

    public void refresh() {
        for (ObjectivePilePanel upPanel : upObjectivePilePanels.values()) {
            upPanel.updateImage();
        }
        for (ObjectivePilePanel downPanel : downObjectivePilePanels.values()) {
            downPanel.updateImage();
        }
        for (MainPilePanel mainPanel : mainPilePanels.values()) {
            mainPanel.updateImage();
        }
        drawPilePanel.updateImage();
        handPanel.updateCards();
        displayLog("Started new game...");
        incrementScore(0);
    }

    public void setLabels(JLabel logLabel, JLabel scoreLabel) {
        this.logLabel = logLabel;
        this.scoreLabel = scoreLabel;
    }

    public void add(ObjectivePilePanel panel) {
        Map<Suit, ObjectivePilePanel> mapToAdd = panel.getDirection() ? upObjectivePilePanels : downObjectivePilePanels;
        mapToAdd.put(panel.getSuit(), panel);
        super.add(panel);
    }

    public void add(MainPilePanel panel) {
        mainPilePanels.put(panel.getPileValue(), panel);
        super.add(panel);
    }

    public void add(DrawPilePanel panel) {
        this.drawPilePanel = panel;
        super.add(panel);
    }

    public void add(HandPanel panel) {
        this.handPanel = panel;
        super.add(panel);
    }

    public ObjectivePilePanel getObjectivePilePanel(Suit suit, boolean direction) {
        Map<Suit, ObjectivePilePanel> mapToSearch = direction ? upObjectivePilePanels : downObjectivePilePanels;
        return mapToSearch.get(suit);
    }

    public MainPilePanel getMainPilePanel(Value value) {
        return mainPilePanels.get(value);
    }

    public DrawPilePanel getDrawPilePanel() {
        return drawPilePanel;
    }

    public HandPanel getHandPanel() {
        return handPanel;
    }

    public void displayLog(String text) {
        logLabel.setText(text);
    }

    public void incrementScore(int increment) {
        model.score += increment;
        boolean isPositive = model.score > 0;
        String newText = isPositive ? "$" + model.score : "($" + (-model.score) + ")";
        scoreLabel.setText(newText);
        scoreLabel.setForeground(isPositive ? Color.BLACK : Color.RED);
    }
}
