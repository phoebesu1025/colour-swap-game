package colourswap.controller.keylisteners;

import colourswap.model.shapes.Player;

import java.awt.event.KeyEvent;

/**
 * KeyListener that controls a player's movement using the arrow keys.
 */
public class ArrowKeyMovementKeyListenerAdapter extends MovementKeyListenerAdapter {

    public ArrowKeyMovementKeyListenerAdapter(Player player) {
        super(player, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    }
}
