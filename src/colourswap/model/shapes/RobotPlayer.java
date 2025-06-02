package colourswap.model.shapes;

import colourswap.model.Colour;

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
// TODO Increment II Task Two and Three.
public class RobotPlayer extends Player {

    public RobotPlayer(int x, int y, Colour colour, int radius) {
        super(x, y, colour, radius);
    }

    /**
     * Decide which direction the player should move in.
     * <p>
     * Move away from the nearest opposite-coloured shape. If there are none nearby, move towards the middle instead.
     *
     * @param shapes The shapes in the game.
     */
    public void decideMovement(List<Shape> shapes) {
        // TODO Increment II Task Two and Three. Complete this method
    }
}
