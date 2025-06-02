package colourswap.view;

import colourswap.model.Game;

import java.awt.event.KeyListener;
import java.util.List;

/**
 * Implementing classes may act as views on a colour-swap game.
 */
public interface GameView {

    /**
     * Sets the game to display
     *
     * @param game the {@link Game}
     */
    void setGame(Game game);

    /**
     * Sets the view's UI to be able to supply key listeners to the controller
     *
     * @param listeners the listeners to add to the view
     */
    void configureInputListeners(List<KeyListener> listeners);

    /**
     * Makes sure the game is in-view in the UI, and that key listeners will be accepted.
     */
    void showGame();

    /**
     * Lets the view know it needs to redraw itself
     */
    void repaint();

    /**
     * Tells the view to display the given score in its UI
     *
     * @param score the score to display
     */
    void displayScore(int score);

    /**
     * Tells the view that the game is over
     *
     * @param score the final score at the time the game ended
     */
    void gameOver(int score);
}
