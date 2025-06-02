package colourswap.model;

import colourswap.model.shapes.*;

/**
 * Random shape generator. This class is used by the game to periodically create obstacles.
 */
public class ShapeFactory {

    public Shape randomShape() {

        // Shape always appears at the top of the screen. The x-coordinate and colour are random.
        int x = (int) (Math.random() * Config.GAME_WIDTH);
        int y = 0;
        Colour colour = Colour.random();

        // Randomly choose a shape type
        // TODO Increment I. Add new shapes appropriately.
        switch ((int) (Math.random() * 2)) {
            case 0:
                return new RectangleShape(x, y, colour, (Config.SHAPE_WIDTH), Config.SHAPE_HEIGHT);
            case 1:
                return new CircleShape(x, y, colour, Config.SHAPE_RADIUS);
            default:
                throw new RuntimeException("switch statement should not reach this line");
        }

    }

}
