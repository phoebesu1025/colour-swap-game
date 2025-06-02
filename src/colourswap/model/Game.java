package colourswap.model;

import colourswap.Painter;
import colourswap.model.shapes.Player;
import colourswap.model.shapes.RobotPlayer;
import colourswap.model.shapes.Shape;
import colourswap.utils.CollisionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Class representing the game. This class is responsible for all game logic. The view layer starts a game by creating
 * an instance of this class and adding a GameListener (i.e. TableAdapter) to the game.
 */
public class Game {

    private List<Shape> obstacles = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private ShapeFactory shapeFactory = new ShapeFactory();
    private int score = 0;
    private int spawnDelay = Config.SHAPE_SPAWN_DELAY;
    private int timeUntilNextObstacle = 0;
    private boolean over;

    // TODO Increment III Task Three Initialise the list
    private List<GameUpdateListener> listeners;

    public Game(List<Player> players) {
        this.players.addAll(players);
    }

    public List<Shape> shapes() {
        return Collections.unmodifiableList(this.obstacles);
    }

    /**
     * Return player corresponding to number.
     * <p>
     * If the player does not exist, throw IndexOutOfBoundsException.
     *
     * @param number The number of the player (either 1 or 2).
     */
    public Player getPlayer(int number) {
        return this.players.get(number - 1);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    public int getScore() {
        return this.score;
    }

    public void drawPlayersAndShapes(Painter painter) {
        for (Player player : this.players) {
            player.draw(painter);
        }
        for (Shape shape : this.obstacles) {
            shape.draw(painter);
        }
    }

    /**
     * Update the game state.
     * <p>
     * This method is responsible for spawning new shapes, moving players and shapes, removing shapes that touch a
     * player, and ending the game.
     */
    public void update() {

        // Periodically add a new obstacle
        this.timeUntilNextObstacle -= 1;
        if (this.timeUntilNextObstacle <= 0) {
            this.obstacles.add(this.shapeFactory.randomShape());
            this.timeUntilNextObstacle = this.spawnDelay;
        }

        // Move obstacles
        for (Shape obstacle : this.obstacles) {
            obstacle.move();
        }

        // Move players
        for (Player player : this.players) {
            if (player instanceof RobotPlayer) {
                ((RobotPlayer) player).decideMovement(this.obstacles);
            }
            player.move();
        }

        // Check shapes touching players
        this.removeShapesTouchingSameColouredPlayer();
        if (this.shapeTouchingOppositeColouredPlayer()) {
            this.over = true;
        }

        // TODO Increment III Task Three. Notify GameUpdateListeners

    }

    /**
     * This method removes all shapes which are touching a player of the same colour.
     */
    private void removeShapesTouchingSameColouredPlayer() {
        boolean touchingPlayer;
        Iterator<Shape> shapeIterator = this.obstacles.iterator();
        while (shapeIterator.hasNext()) {
            Shape shape = shapeIterator.next();
            touchingPlayer = false;
            for (Player player : this.players) {
                if (CollisionUtils.isColliding(shape, player) && shape.getColour() == player.getColour()) {
                    touchingPlayer = true;
                }
            }
            if (touchingPlayer) {
                this.score += shape.getScore();
                shapeIterator.remove();
            }
        }
    }

    /**
     * This method returns a value indicating whether any shape is touching a player of a different colour
     *
     * @return true if such a shape is touching a player; false otherwise
     */
    private boolean shapeTouchingOppositeColouredPlayer() {
        for (Shape shape : this.obstacles) {
            for (Player player : this.players) {
                if (CollisionUtils.isColliding(shape, player) && shape.getColour() != player.getColour()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets a value indicating whether the game is over
     *
     * @return true if the game is over; false otherwise
     */
    public boolean isOver() {
        return this.over;
    }

    /**
     * Adds an update listener to be notified whenever a game update occurs, such as a shape being added, removed,
     * or having its position updated.
     *
     * @param l the listener to add
     *          <p>
     */
    public void addGameListener(GameUpdateListener l) {
        // TODO Increment III Task Three. Complete this method to add GameUpdateListener
    }
}
