package colourswap.controller;

import colourswap.controller.keylisteners.ArrowKeyMovementKeyListenerAdapter;
import colourswap.controller.keylisteners.ColourSwapKeyListenerAdapter;
import colourswap.model.Colour;
import colourswap.model.Config;
import colourswap.model.Game;
import colourswap.model.shapes.Player;
import colourswap.model.shapes.RobotPlayer;
import colourswap.view.GameView;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * A class which controls the setup of new games, ticking running games, and hooking up a game with its views.
 */
public class GameController {

    /**
     * The timer which will tick to update the game
     */
    private Timer timer;

    /**
     * The game currently being played
     */
    private Game game;

    /**
     * The view which is displaying the game
     */
    private GameView view;

    /**
     * Creates the GameController and sets up the timer.
     */
    public GameController() {
        this.timer = new Timer(20, (e) -> this.timerTick());
    }

    /**
     * Sets the game view to the given object
     * <p>
     * Currently, this doesn't work if called after starting the game; that is outside the scope of this assignment.
     *
     * @param view the game view to display the game
     */
    public void setView(GameView view) {
        this.view = view;
    }

    /**
     * Starts a new game in the given game mode
     *
     * @param mode {@link GameMode}
     */
    public void startNewGame(GameMode mode) {
        if (this.timer.isRunning()) {
            this.timer.stop();
        }

        List<KeyListener> inputListeners;

        switch (mode) {
            case SINGLE_PLAYER:
                // Configure a new game with a single player controlled by the arrow keys
                this.game = new Game(List.of(new Player(Config.GAME_WIDTH / 2 + 100, Config.GAME_HEIGHT / 2, Colour.RED, Config.PLAYER_RADIUS)));
                inputListeners = List.of(new ArrowKeyMovementKeyListenerAdapter(game.getPlayer(1)), new ColourSwapKeyListenerAdapter(game));
                break;

            case PLAY_WITH_ROBOT:
                // TODO Increment II Task One.(v)
                // Configure a new game with a single human player controlled by the arrow keys, and a robot player.
                // Human player at one side
                Player human = new Player(Config.GAME_WIDTH / 2 + 100, Config.GAME_HEIGHT / 2, Colour.RED, Config.PLAYER_RADIUS);

                // Robot player at the other side with opposite colour
                Player robot = new RobotPlayer(Config.GAME_WIDTH / 2 - 100, Config.GAME_HEIGHT / 2, Colour.BLUE, Config.PLAYER_RADIUS);


                // Create a new game with both players
                this.game = new Game(List.of(human, robot));
                inputListeners = List.of(
                        new ArrowKeyMovementKeyListenerAdapter(game.getPlayer(1)), // player 1 = human
                        new ColourSwapKeyListenerAdapter(game)
                );
                break;

            default:
                throw new IllegalArgumentException("Unsupported mode");
        }

        // Display the game in the view, and start the timer ticking
        this.view.setGame(this.game);
        this.view.configureInputListeners(inputListeners);
        this.view.showGame();
        this.timer.start();
    }

    /**
     * This method is called by the timer every time it ticks. It will:
     * - Update the game
     * - Stop the timer if the game is over
     * - Display the current score
     * - Tell the view it needs to redraw itself
     */
    private void timerTick() {
        if (this.game.isOver()) {
            this.timer.stop();
            this.view.gameOver(this.game.getScore());
        }

        int oldScore = this.game.getScore();
        game.update();

        if (oldScore == this.game.getScore()) {
            this.view.displayScore(this.game.getScore());
        }

        this.view.repaint();
    }
}
