package colourswap.model;

/**
 * Enum for the two possible shape colours RED and BLUE. Note that this class is NOT the same class as java.awt.Color.
 * The view layer (i.e. GraphicsPainter) is responsible for converting Colour to Color.
 */
public enum Colour {

    RED, BLUE;

    /**
     * Gets the opposite colour.
     *
     * @return {@link #RED} if we are {@link #BLUE}, and vice versa
     */
    public Colour opposite() {
        if (this == Colour.RED) {
            return Colour.BLUE;
        } else {
            return Colour.RED;
        }
    }

    /**
     * Returns a random colour.
     *
     * @return either {@link #RED} or {@link #BLUE}, at random.
     */
    public static Colour random() {
        if (Math.random() < 0.5) return Colour.RED;
        return Colour.BLUE;
    }

}
