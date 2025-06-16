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
        // TODO Increment I. Add new shapes appropriately. (v)
        switch ((int) (Math.random() * 5)) {
            case 0:
                return new RectangleShape(x, y, colour, (Config.SHAPE_WIDTH), Config.SHAPE_HEIGHT);
            case 1:
                return new CircleShape(x, y, colour, Config.SHAPE_RADIUS);
            case 2:
                return new BlinkingShape(x, y, colour, Config.SHAPE_WIDTH, Config.SHAPE_HEIGHT);
            case 3:
                return new TShape(x, y, colour, Config.SHAPE_WIDTH, Config.SHAPE_HEIGHT);
            case 4:
                // Level 1: outer container
                NestedShape outer = new NestedShape(x, y, colour, Config.SHAPE_WIDTH + 30, Config.SHAPE_HEIGHT + 20);
                // so we can see rec and other shape bouncing around

                // Level 2: middle container (a random nested shape)
                NestedShape middle = new NestedShape(0, 0, colour, Config.SHAPE_WIDTH, Config.SHAPE_HEIGHT);

                // Level 3: basic shape inside middle
                Shape innerChild = createRandomNestedShape(0, 0, colour);
                middle.addInnerShape(innerChild);

                // Add middle into outer
                outer.addInnerShape(middle);

                return outer;

            default:
                throw new RuntimeException("switch statement should not reach this line");
        }
    }

    private Shape createRandomNestedShape(int x, int y, Colour colour) {
        int shapeType = (int) (Math.random() * 3); // 0=Rectangle, 1=Circle, 2=TShape
        switch (shapeType) {
            case 0:
                return new RectangleShape(x, y, colour, Config.SHAPE_WIDTH, Config.SHAPE_HEIGHT);
            case 1:
                return new CircleShape(x, y, colour, Config.SHAPE_RADIUS);
            case 2:
                return new TShape(x, y, colour, Config.SHAPE_WIDTH, Config.SHAPE_HEIGHT);
            case 3:
                return new BlinkingShape(x, y, colour, Config.SHAPE_WIDTH, Config.SHAPE_HEIGHT);
            default:
                throw new RuntimeException("Unexpected shape type");
        }
    }
}
