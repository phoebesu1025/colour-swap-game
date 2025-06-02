package colourswap.model;

/**
 * Interface used by listeners interested in updates to the game.
 */
public interface GameUpdateListener {

    /**
     * Called when the game state changes
     */
    void gameStateUpdated();
}
