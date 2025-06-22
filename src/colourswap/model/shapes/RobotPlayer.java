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
// TODO Increment II Task Two and Three. (v)
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
        // TODO Increment II Task Two and Three. Complete this method (v)
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
        int shapeEndX = shape.getX() + this.getWidth();
        int shapeY = shape.getY();
        int shapeEndY = shape.getY() + this.getHeight();
        int robotX = this.getX();
        int robotEndX = this.getX() + this.getWidth();
        int robotY = this.getY();
        int robotEndY = this.getY() + this.getHeight();

        if (handleCornerCases(atLeftEdge, atRightEdge, atTopEdge, atBottomEdge, shapeX, shapeY, shapeEndX, shapeEndY, robotX, robotY, robotEndX, robotEndY)) {
            return;
        }

        if (handleEdgeCases(atLeftEdge, atRightEdge, atTopEdge, atBottomEdge, shapeX, shapeY, robotX, robotY)) {
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

    private boolean handleCornerCases(boolean atLeftEdge, boolean atRightEdge, boolean atTopEdge, boolean atBottomEdge, int shapeX, int shapeY, int shapeEndX, int shapeEndY, int robotX, int robotY, int robotEndX, int robotEndY) {
        if (atLeftEdge && atTopEdge) {
            if (shapeX < robotEndX) startMovingRight();
            if (shapeY < robotEndY) startMovingDown();
            return true;
        } else if (atRightEdge && atTopEdge) {
            if (shapeEndX > robotX) startMovingLeft();
            if (shapeY < robotEndY) startMovingDown();
            return true;
        } else if (atLeftEdge && atBottomEdge) {
            if (shapeX < robotEndX) startMovingRight();
            if (shapeEndY > robotY) startMovingUp();
            return true;
        } else if (atRightEdge && atBottomEdge) {
            if (shapeEndX > robotX) startMovingLeft();
            if (shapeEndY > robotY) startMovingUp();
            return true;
        }
        return false;
    }

    private boolean handleEdgeCases(boolean atLeftEdge, boolean atRightEdge, boolean atTopEdge, boolean atBottomEdge, int shapeX, int shapeY, int robotX, int robotY) {
        if (atLeftEdge || atRightEdge) {
            if (shapeY < robotY && !atBottomEdge) startMovingDown();
            else if (shapeY > robotY && !atTopEdge) startMovingUp();
            return true;
        }

        if (atTopEdge || atBottomEdge) {
            if (shapeX < robotX && !atRightEdge) startMovingRight();
            else if (shapeX > robotX && !atLeftEdge) startMovingLeft();
            return true;
        }
        return false;
    }

    private void moveTowardCenterIfFar() {
        int targetX = Config.GAME_WIDTH / 2;
        int targetY = Config.GAME_HEIGHT / 2;

        double distanceFromCenter = this.getDistanceToPoint(targetX, targetY);

        if (distanceFromCenter >= Config.ROBOT_RETURN_DISTANCE) {
            int dx = targetX - this.centeredX();
            int dy = targetY - this.centeredY();

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
