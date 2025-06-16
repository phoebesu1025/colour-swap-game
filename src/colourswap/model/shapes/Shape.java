package colourswap.model.shapes;

import colourswap.Painter;
import colourswap.model.Colour;
import colourswap.model.Config;
import colourswap.utils.DistanceUtils;

/**
 * Abstract superclass for all shapes, including both players and obstacles. This class defines state common to all
 * shapes, methods for drawing shapes, and methods for moving shapes. Subclasses override various methods as needed.
 */
public abstract class Shape {

    protected int x;
    protected int y;
    protected Colour colour;
    protected int width;
    protected int height;
    protected int dx;
    protected int dy;
    protected int borderWidth;
    protected int borderHeight;
    private boolean suppressScore = false;

    /**
     * Create a new Shape.
     *
     * @param x      the x-coordinate of the top-left corner
     * @param y      the y-coordinate of the top-left corner
     * @param colour the colour of the shape
     * @param width  the width of the shape
     * @param height the height of the shape
     */
    public Shape(int x, int y, Colour colour, int width, int height) {
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.width = width;
        this.height = height;
        this.dx = Config.SHAPE_SPEED;
        this.dy = Config.SHAPE_SPEED;
        this.borderWidth = Config.GAME_WIDTH;
        this.borderHeight = Config.GAME_HEIGHT;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getBorderWidth() {
        return this.borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getBorderHeight() {
        return this.borderHeight;
    }

    public void setBorderHeight(int borderHeight) {
        this.borderHeight = borderHeight;
    }

    public Colour getColour() {
        return this.colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public int getDx() {
        return this.dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return this.dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int centeredX() {
        return this.x + this.width / 2;
    }

    public int centeredY() {
        return this.y + this.height / 2;
    }

    /**
     * Draw the shape.
     *
     * @param painter The painter used to draw the shape.
     */
    public void draw(Painter painter) {
        painter.setColour(this.colour);
        this.drawShape(painter);

        if (shouldDisplayScore()) {
            int centerX = this.getX() + this.getWidth() / 2;
            int centerY = this.getY() + this.getHeight() / 2;
            painter.drawString(String.valueOf(this.getScore()), centerX, centerY);
        }
    }

    protected abstract void drawShape(Painter painter);

    /**
     * Update the shape's xy-coordinates based on its current direction.
     */
    public void move() {

        // Change direction if shape is touching edge
        if (this.x <= 0) {
            this.dx = Math.abs(this.dx);
        } else if (this.x + this.width >= this.borderWidth) {
            this.dx = -Math.abs(this.dx);
        }
        if (this.y <= 0) {
            this.dy = Math.abs(this.dy);
        } else if (this.y + this.height >= this.borderHeight) {
            this.dy = -Math.abs(this.dy);
        }

        // Update coordinates using direction
        this.x += this.dx;
        this.y += this.dy;

    }

    /**
     * Determines the distance from this shape to the given point.
     * Assumes this shape is a rectangle. Override in subclasses to change this behaviour.
     *
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @return the shortest distance from this shape to that point.
     */
    public double getDistanceToPoint(int pX, int pY) {
        return DistanceUtils.rectangleDistanceToPoint(this.x, this.y, this.width, this.height, pX, pY);
    }

    // TODO: Increment III Task One. Add a method that returns this shape's name.
    /**
     * Returns the name of this shape.
     *
     * @return the shape name
     */
    protected abstract String name();

    /**
     * Return this shape's score
     */
    public int getScore() {
        return 1;
    }

    public void setSuppressScore(boolean suppress) {
        this.suppressScore = suppress;
    }

    /**
     * Return true if the score is not suppress - for NestedShape
     */
    public boolean shouldDisplayScore() {
        return !suppressScore;
    }



}
