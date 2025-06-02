package colourswap.view;

import colourswap.GraphicsPainter;
import colourswap.Painter;
import colourswap.model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Panel used to display shapes.
 */
public class ShapePanel extends JPanel {

    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Painter painter = new GraphicsPainter(g);
        this.game.drawPlayersAndShapes(painter);
    }

}
