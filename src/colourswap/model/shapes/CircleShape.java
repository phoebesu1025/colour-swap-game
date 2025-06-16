package colourswap.model.shapes;

import colourswap.Painter;
import colourswap.model.Colour;
import colourswap.utils.DistanceUtils;

/**
 * Shape subclass for simple circular shapes. This class is responsible for both players (instantiated from Player,
 * which subclasses this) and obstacles (instantiated from this class directly)
 */
public class CircleShape extends Shape {

    public CircleShape(int x, int y, Colour colour, int radius) {
        // Note that CircleShape has only a radius instead of width, height
        super(x, y, colour, radius * 2, radius * 2);
    }

    public int getRadius() {
        return this.width / 2;
    }

    /**
     * Overridden in this class to use circle-to-point distance calculation.
     *
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @return the shortest distance from this circle to that point
     */
    @Override
    public double getDistanceToPoint(int pX, int pY) {
        return DistanceUtils.circleDistanceToPoint(this.centeredX(), this.centeredY(), this.getRadius(), pX, pY);
    }

    @Override
    protected String name() {
        return "CircleShape";
    }

    @Override
    public void drawShape(Painter painter) {
        painter.fillOval(this.colour, this.x, this.y, this.width, this.height);
    }

}
