package colourswap.controller.keylisteners;

import colourswap.model.shapes.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyListener that controls a player's movement using the configured keys.
 */
public class MovementKeyListenerAdapter implements KeyListener {

    private Player player;
    private int keyUp;
    private int keyDown;
    private int keyLeft;
    private int keyRight;

    /**
     * Creates a new MovementKeyListenerAdapter with the configured keys
     *
     * @param player   the player to move
     * @param keyUp    the key to press to move up
     * @param keyDown  the key to press to move down
     * @param keyLeft  the key to press to move left
     * @param keyRight the key to press to move right
     */
    public MovementKeyListenerAdapter(Player player, int keyUp, int keyDown, int keyLeft, int keyRight) {
        this.player = player;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    /**
     * Starts the player moving in a particular direction if the corresponding key is pressed
     *
     * @param e the event to be processed; contains information about the key which was pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if (keyCode == this.keyUp) {
            this.player.startMovingUp();
        } else if (keyCode == this.keyDown) {
            this.player.startMovingDown();
        } else if (keyCode == this.keyLeft) {
            this.player.startMovingLeft();
        } else if (keyCode == this.keyRight) {
            this.player.startMovingRight();
        }
    }

    /**
     * Stops the player moving in a particular direction if the corresponding key was released
     *
     * @param e the event to be processed; contains information about the key which was released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == this.keyUp) {
            this.player.stopMovingUp();
        } else if (keyCode == this.keyDown) {
            this.player.stopMovingDown();
        } else if (keyCode == this.keyLeft) {
            this.player.stopMovingLeft();
        } else if (keyCode == this.keyRight) {
            this.player.stopMovingRight();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is not needed
    }

}
