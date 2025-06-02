package colourswap.utils;

import colourswap.model.shapes.Player;
import colourswap.model.shapes.Shape;

public class CollisionUtils {

    /**
     * Gets a value indicating whether the given shape is colliding with the given player
     *
     * @param shape  the shape to test
     * @param player the player to test
     * @return true if the two are colliding, false otherwise.
     */
    public static boolean isColliding(Shape shape, Player player) {
        return shape.getDistanceToPoint(player.centeredX(), player.centeredY()) <= player.getRadius();
    }
}
