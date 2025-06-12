package colourswap.model.shapes;

import colourswap.Painter;
import colourswap.model.Colour;
import colourswap.utils.DistanceUtils;

public class TShape extends Shape {
   private int blockSize;

    public TShape(int x, int y, Colour colour, int width, int height) {
        super(x, y, colour, width, height);
        this.blockSize = width / 3;
        // Divide the rect shape into 3: 60/3 = 20 to calculate my TShape block.
    }

    @Override
    protected void drawShape(Painter painter) {
        painter.translate(this.x, this.y);

        // Top row: 3 blocks side by side (x = 0, 1, 2)
        for (int i = 0; i < 3; i++) {
            painter.fillRect(this.colour, i * blockSize, 0, blockSize, blockSize);
        }

        // Bottom center block (x = 1)
        painter.fillRect(this.colour, blockSize, blockSize, blockSize, blockSize);

        painter.translate(-this.x, -this.y); // reset after drawing
    }

    @Override
    public double getDistanceToPoint(int px, int py) {
        // set up a starting point for finding the smallest distance
        double minDist = Double.MAX_VALUE;

        // Top row blocks
        for (int i = 0; i < 3; i++) {
            double distance = DistanceUtils.rectangleDistanceToPoint(
                    x + i * blockSize, y, blockSize, blockSize, px, py
            );
            minDist = Math.min(minDist, distance);
        }

        // Bottom center block
        double bottomDist = DistanceUtils.rectangleDistanceToPoint(
                x + blockSize, y + blockSize, blockSize, blockSize, px, py
        );
        return Math.min(minDist, bottomDist);
    }
}
