package colourswap.model.shapes;

import colourswap.model.Colour;
import colourswap.model.Config;

/**
 * Shape subclass for the player. The player is a special circle that is controlled by keyboard input (or AI).
 */
public class Player extends CircleShape {

    /* Before the game moves players, the game first checks these four flags to determine which direction the player
    needs to move in. The player should set these as needed. */
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public Player(int x, int y, Colour colour, int radius) {
        // Note that players use movement flags to determine movement direction instead of dx, dy
        super(x, y, colour, radius);
    }

    public void startMovingUp() {
        this.movingUp = true;
    }

    public void stopMovingUp() {
        this.movingUp = false;
    }

    public void startMovingDown() {
        this.movingDown = true;
    }

    public void stopMovingDown() {
        this.movingDown = false;
    }

    public void startMovingLeft() {
        this.movingLeft = true;
    }

    public void stopMovingLeft() {
        this.movingLeft = false;
    }

    public void startMovingRight() {
        this.movingRight = true;
    }

    public void stopMovingRight() {
        this.movingRight = false;
    }

    /**
     * Update the player's coordinates based on its current direction. Do not allow the player to move past the edge of
     * the screen.
     */
    @Override
    public void move() {
        if (this.movingUp && this.y > 0) {
            this.y -= Config.PLAYER_SPEED;
        }
        if (this.movingDown && this.y + this.height < this.borderHeight) {
            this.y += Config.PLAYER_SPEED;
        }
        if (this.movingLeft && this.x > 0) {
            this.x -= Config.PLAYER_SPEED;
        }
        if (this.movingRight && this.x + this.width < this.borderWidth) {
            this.x += Config.PLAYER_SPEED;
        }
    }

}
