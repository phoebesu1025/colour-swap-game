package colourswap;

import colourswap.model.Colour;

/**
 * Subclass of Painter used for testing purposes.
 */
public class MockPainter implements Painter {

    private Colour colour;
    private StringBuffer output = new StringBuffer();

    @Override
    public void setColour(Colour colour) {
        this.colour = colour;
    }

    @Override
    public void drawString(String str, int x, int y) {
        // Do nothing
    }

    @Override
    public void fillOval(Colour colour, int x, int y, int width, int height) {
        this.output.append(String.format("fillOval %d %d %d %d %s\n", x, y, width, height, this.colour));
    }

    @Override
    public void fillRect(Colour colour, int x, int y, int width, int height) {
        this.output.append(String.format("fillRect %d %d %d %d %s\n", x, y, width, height, this.colour));
    }

    @Override
    public void translate(int x, int y) {
        this.output.append(String.format("translate %d %d\n", x, y));
    }

    public String output() {
        return this.output.toString();
    }

}
