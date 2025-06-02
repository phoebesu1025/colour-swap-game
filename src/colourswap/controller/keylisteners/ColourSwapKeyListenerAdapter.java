package colourswap.controller.keylisteners;

import colourswap.model.Game;
import colourswap.model.shapes.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyListener that swaps colours for all players using the space bar.
 */
public class ColourSwapKeyListenerAdapter implements KeyListener {

    private Game game;

    public ColourSwapKeyListenerAdapter(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Do not accept keyboard input if game is already over
        if (this.game.isOver()) {
            return;
        }

        // Ignore all keys other than space
        if (!(e.getKeyCode() == KeyEvent.VK_SPACE)) {
            return;
        }

        // Swap colours for all players
        for (Player player : game.getPlayers()) {
            player.setColour(player.getColour().opposite());
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // This method is not needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is not needed
    }

}
