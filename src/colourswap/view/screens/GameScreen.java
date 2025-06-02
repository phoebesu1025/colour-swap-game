package colourswap.view.screens;

import colourswap.model.Config;
import colourswap.model.Game;
import colourswap.view.MainWindow;
import colourswap.view.ShapePanel;

import javax.swing.*;
import java.awt.*;

/**
 * The screen that displays the game. This screen contains a ShapePanel on the left and a JTable on the right.
 */
public class GameScreen extends JPanel {

    private Game game;
    private ShapePanel shapePanel;
    private JTable table;
    private JLabel scoreLabel;

    public GameScreen(MainWindow mainWindow) {
        this.setLayout(new BorderLayout());

        // This is the left panel, containing the shapes
        this.shapePanel = new ShapePanel();
        this.shapePanel.setPreferredSize(new Dimension(Config.GAME_WIDTH, Config.GAME_HEIGHT));
        this.add(this.shapePanel, BorderLayout.WEST);

        // This is the right panel, containing the score and table
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.CENTER);

        // This displays the score
        this.scoreLabel = new JLabel();
        infoPanel.add(this.scoreLabel, BorderLayout.NORTH);

        // This displays the table
        // TODO Increment III Task Three. Uncomment the following three lines to display the table
//        this.table = new JTable();
//        JScrollPane tableScrollPane = new JScrollPane(table);
//        infoPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    /**
     * Replace the game being handled by the game panel.
     *
     * @param game The new game instance.
     */
    public void setGame(Game game) {
        this.game = game;
        this.shapePanel.setGame(game);

        // TODO Increment III Task Three. Link the JTable with Game and TableAdapter

    }

    /**
     * Displays the given score in the score label
     *
     * @param score the score to display
     */
    public void setScore(int score) {
        this.scoreLabel.setText("score: " + game.getScore());
    }
}
