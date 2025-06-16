package colourswap.model.shapes;

import colourswap.model.Colour;
import colourswap.model.Config;

import java.util.List;

/**
 * Player subclass for robot-controlled players.
 * <p>
 * The robot tries to move away from any nearby opposite-coloured obstacles. If there are none, the robot instead
 * returns roughly to the middle of the game area.
 * <p>
 * Config.ROBOT_AVOID_DISTANCE determines the minimum distance the robot tries to keep itself away from
 * opposite-coloured obstacles. Config.ROBOT_RETURN_DISTANCE controls the maximum distance the robot tries to keep
 * itself from the centre.
 * <p>
 * The robot does not decide when to change colour. This responsibility remains with the human player.
 */
public class RobotPlayer extends Player {

    public RobotPlayer(int x, int y, Colour colour, int radius) {
        super(x, y, colour, radius);
    }

    /**
     * Decide which direction the player should move in.
     * Move away from the nearest opposite-coloured shape. If there are none nearby, move towards the middle instead.
     *
     * @param shapes The shapes in the game.
     */
    public void decideMovement(List<Shape> shapes) {
        // Stop all movement first
        stopMovingUp();
        stopMovingDown();
        stopMovingLeft();
        stopMovingRight();

        Shape closestThreat = findClosestOppositeWithinDistance(shapes, Config.ROBOT_AVOID_DISTANCE);
        if (closestThreat != null) {
            moveAwayFrom(closestThreat);
        } else {
            moveTowardCenterIfFar();
        }
    }

    private void moveAwayFrom(Shape shape) {
        boolean atLeftEdge = this.getX() <= 0;
        boolean atRightEdge = this.getX() + this.getWidth() >= Config.GAME_WIDTH;
        boolean atTopEdge = this.getY() <= 0;
        boolean atBottomEdge = this.getY() + this.getHeight() >= Config.GAME_HEIGHT;

        int shapeX = shape.getX();
        int shapeY = shape.getY();
        int robotX = this.getX();
        int robotY = this.getY();

        // Special edge fallback: if stuck against left or right edge, move vertically away
        if (atLeftEdge || atRightEdge) {
            if (shapeY < robotY && !atBottomEdge) {
                startMovingDown();
            } else if (shapeY > robotY && !atTopEdge) {
                startMovingUp();
            }
            return;
        }

        // Diagonal escape logic
        if (shapeX < robotX && shapeY < robotY) {
            if (!atRightEdge) startMovingRight();
            if (!atTopEdge) startMovingUp();
        } else if (shapeX > robotX && shapeY < robotY) {
            if (!atLeftEdge) startMovingLeft();
            if (!atTopEdge) startMovingUp();
        } else if (shapeX > robotX && shapeY > robotY) {
            if (!atLeftEdge) startMovingLeft();
            if (!atBottomEdge) startMovingDown();
        } else if (shapeX < robotX && shapeY > robotY) {
            if (!atRightEdge) startMovingRight();
            if (!atBottomEdge) startMovingDown();
        }
    }

    private void moveTowardCenterIfFar() {
        double distanceFromCenter = this.getDistanceToPoint(Config.GAME_WIDTH/2, Config.GAME_HEIGHT/2);

        if (distanceFromCenter >= Config.ROBOT_RETURN_DISTANCE) {
            if (dx > 0 && this.getX() + this.getWidth() < Config.GAME_WIDTH) {
                startMovingRight();
            } else if (dx < 0 && this.getX() > 0) {
                startMovingLeft();
            }

            if (dy > 0 && this.getY() + this.getHeight() < Config.GAME_HEIGHT) {
                startMovingDown();
            } else if (dy < 0 && this.getY() > 0) {
                startMovingUp();
            }
        }
    }

    private Shape findClosestOppositeWithinDistance(List<Shape> shapes, int maxDistance) {
        Shape closest = null;
        double minDistance = Double.MAX_VALUE;
        int robotCenterX = this.centeredX();
        int robotCenterY = this.centeredY();

        for (Shape shape : shapes) {
            if (shape == this || shape.getColour().equals(this.getColour())) {
                continue;
            }

            // Use shape's getDistanceToPoint for edge-aware distance
            double distance = shape.getDistanceToPoint(robotCenterX, robotCenterY);

            if (distance <= maxDistance && distance < minDistance) {
                closest = shape;
                minDistance = distance;
            }
        }
        return closest;
    }
}
