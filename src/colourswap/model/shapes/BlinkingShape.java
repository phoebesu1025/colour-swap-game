package colourswap.model.shapes;

import colourswap.model.Colour;
import colourswap.model.Config;

// Extending from RectangleShape, but will change colour periodically
public class BlinkingShape extends RectangleShape {
    private int ticks = 0;
    private int score = 1;

    public BlinkingShape(int x, int y, Colour colour, int width, int height) {
        super(x, y, colour, width, height);
    }

    @Override
    public void move() {
        super.move();

        ticks++; // count how many times the shape has moved

        if (ticks >= Config.SHAPE_BLINK_DELAY) {
            // Checks if the shape has moved enough times (ticks) to flip colour.
            this.setColour(this.getColour().opposite()); // Blink
            score++;
            ticks = 0; // Reset tick counter
        }
    }

    @Override
    protected String name() {
        return "BlinkingShape";
    }

    @Override
    public int getScore() {
        return score;
    }
}
