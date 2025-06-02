package colourswap.model.shapes;

import colourswap.Painter;
import colourswap.model.Colour;

/**
 * Shape subclass for simple rectangular shapes.
 */
public class RectangleShape extends Shape {

    public RectangleShape(int x, int y, Colour colour, int width, int height) {
        super(x, y, colour, width, height);
    }

    @Override
    public void drawShape(Painter painter) {
        painter.fillRect(this.colour, this.x, this.y, this.width, this.height);
    }

}
