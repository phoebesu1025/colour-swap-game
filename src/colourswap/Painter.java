package colourswap;

import colourswap.model.Colour;

/**
 * Interface used by objects that draw shapes.
 */
public interface Painter {

    void setColour(Colour colour);

    void drawString(String str, int x, int y);

    void fillOval(Colour colour, int x, int y, int width, int height);

    void fillRect(Colour colour, int x, int y, int width, int height);

    void translate(int x, int y);

}
