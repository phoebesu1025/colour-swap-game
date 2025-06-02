package colourswap.view;

import colourswap.controller.GameController;
import colourswap.model.Game;
import colourswap.view.screens.GameOverScreen;
import colourswap.view.screens.GameScreen;
import colourswap.view.screens.HowToPlayScreen;
import colourswap.view.screens.TitleScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Top-level class for the graphical user interface. This panel uses a CardLayout to display each of the screens in the
 * application. This class acts as a game view and listener
 */
public class MainWindow extends JPanel implements GameView {

    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;

    /**
     * Creates the main application window
     *
     * @param gameController A {@link GameController} object which controls the creation and running of games.
     */
    public MainWindow(GameController gameController) {

        // Set the game controller's view to this, so that it can tell this object to display new games when they are created.
        gameController.setView(this);

        this.setLayout(new CardLayout());

        // This screen displays the main menu
        JPanel titleScreen = new TitleScreen(this, gameController);
        this.add(titleScreen, "title");

        // These screens display the game instructions
        JPanel howToPlayScreen1 = new HowToPlayScreen(
                this,
                "assets/how-to-play-1.png",
                "<html>Use arrow keys to move and space to change colour.</html>",
                "how to play 2");
        this.add(howToPlayScreen1, "how to play 1");

        JPanel howToPlayScreen2 = new HowToPlayScreen(
                this,
                "assets/how-to-play-2.png",
                "<html>Avoid shapes of the opposite colour.</html>",
                "how to play 3");
        this.add(howToPlayScreen2, "how to play 2");

        JPanel howToPlayPanel3 = new HowToPlayScreen(
                this,
                "assets/how-to-play-3.png",
                "<html>You score points when you touch shapes of the same colour.</html>",
                "how to play 4");
        this.add(howToPlayPanel3, "how to play 3");

        JPanel howToPlayScreen4 = new HowToPlayScreen(
                this,
                "assets/how-to-play-4.png",
                "<html><em>Play with Robot</em> mode: the robot controls the second player.</html>",
                "title");
        this.add(howToPlayScreen4, "how to play 4");

        // This screen allows the user to play the game
        this.gameScreen = new GameScreen(this);
        this.add(this.gameScreen, "game");

        // This screen is shown when the player eventually loses
        this.gameOverScreen = new GameOverScreen(this);
        this.add(this.gameOverScreen, "game over");

    }

    /**
     * Show a different screen.
     *
     * @param name The name of the new screen to show.
     */
    public void showScreen(String name) {
        CardLayout layout = (CardLayout) this.getLayout();
        layout.show(this, name);
    }

    /**
     * When a new game is created, the game controller will supply it to this window via this method.
     *
     * @param game the {@link Game} which should be displayed in this window
     */
    @Override
    public void setGame(Game game) {
        this.gameScreen.setGame(game);
    }

    /**
     * Before a new game starts, the controller will call this method to add any key listeners to the window,
     * so the game can receive user input.
     *
     * @param listeners the listeners to add to the view
     */
    @Override
    public void configureInputListeners(List<KeyListener> listeners) {
        for (KeyListener listener : listeners) {
            this.gameScreen.addKeyListener(listener);
        }
    }

    /**
     * Shows the game screen and requests focus so that its key events can be received
     */
    @Override
    public void showGame() {
        this.showScreen("game");
        this.gameScreen.requestFocusInWindow();
    }

    @Override
    public void displayScore(int score) {
        this.gameScreen.setScore(score);
    }

    /**
     * Shows the "game over" panel with the given score
     *
     * @param score The score to display on the game over panel.
     */
    @Override
    public void gameOver(int score) {
        this.gameOverScreen.setScore(score);
        this.showScreen("game over");
    }
}
