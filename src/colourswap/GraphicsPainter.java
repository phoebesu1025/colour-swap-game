package colourswap;

import colourswap.model.Colour;

import java.awt.*;

/**
 * Subclass of Painter that draws shapes on the screen.
 */
public class GraphicsPainter implements Painter {

    private Graphics graphics; // The graphics object from Swing
    private Colour colour;

    public GraphicsPainter(Graphics g) {
        this.graphics = g;
    }

    @Override
    public void setColour(Colour colour) {
        this.colour = colour;
    }

    @Override
    public void drawString(String str, int x, int y) {

        // Use FontMetrics to centre the string
        FontMetrics metrics = this.graphics.getFontMetrics();
        int actualX = x - metrics.stringWidth(str) / 2;
        int actualY = y - metrics.getHeight() / 2 + metrics.getAscent();

        // Draw the string
        this.graphics.setColor(new Color(0, 0, 0));
        this.graphics.drawString(str, actualX, actualY);

    }

    @Override
    public void fillOval(Colour colour, int x, int y, int width, int height) {
        setColour();
        this.graphics.fillOval(x, y, width, height);
    }

    @Override
    public void fillRect(Colour colour, int x, int y, int width, int height) {
        setColour();
        this.graphics.fillRect(x, y, width, height);
    }

    @Override
    public void translate(int x, int y) {
        this.graphics.translate(x, y);
    }

    private void setColour() {
        switch (this.colour) {
            case RED:
                this.graphics.setColor(new Color(217, 75, 75));
                break;
            case BLUE:
                this.graphics.setColor(new Color(50, 124, 196));
                break;
        }
    }

}
