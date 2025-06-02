package colourswap.utils;

/**
 * Utils for calculating distances between circles, rectangles, and points.
 */
public class DistanceUtils {

    /**
     * Calculates the shortest distance from a rectangle to a given point
     *
     * @param rX the x coordinate of the top-left of the rectangle
     * @param rY the y coordinate of the top-left of the rectangle
     * @param rW the width of the rectangle
     * @param rH the height of the rectangle
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @return the shortest distance between the rectangle and the point
     */
    public static double rectangleDistanceToPoint(int rX, int rY, int rW, int rH, int pX, int pY) {
        int closestX = clamp(pX, rX, rX + rW);
        int closestY = clamp(pY, rY, rY + rH);
        return pointToPointDistance(closestX, closestY, pX, pY);
    }

    /**
     * Calculates the distance between two points
     *
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the first point
     * @param x2 the x coordinate of the second point
     * @param y2 the y coordinate of the second point
     * @return the distance between the two points
     */
    public static double pointToPointDistance(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the shortest distance between the given circle and the given point
     *
     * @param cX the x coordinate of the center of the circle
     * @param cY the y coordinate of the center of the circle
     * @param cR the radius of the circle
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @return the shortest distance between the circle and the point
     */
    public static double circleDistanceToPoint(int cX, int cY, int cR, int pX, int pY) {
        return pointToPointDistance(cX, cY, pX, pY) - cR;
    }

    /**
     * Clamps a value between a minimum and maximum range.
     * If the value is less than the minimum, returns the minimum.
     * If the value is greater than the maximum, returns the maximum.
     * Otherwise, returns the value itself.
     * <p>
     * Useful for constraining values within bounds, such as keeping
     * coordinates within screen or shape limits.
     *
     * @param value The value to clamp.
     * @param min   The minimum allowable value.
     * @param max   The maximum allowable value.
     * @return The clamped value.
     */
    private static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
}
