package colourswap.model.shapes;

import colourswap.Painter;
import colourswap.model.Colour;
import colourswap.model.Config;

import java.util.ArrayList;
import java.util.List;

public class NestedShape extends Shape {
    private int margin = Config.NESTED_SHAPE_MARGIN_SIZE;
    private List<Shape> children = new ArrayList<>();

    public NestedShape(int x, int y, Colour colour, int width, int height) {
        super(x, y, colour, width, height);
    }

    public void addInnerShape(Shape child) {
        child.setX(0);
        child.setY(0);

        // Set bounds so child can't move outside if it ever moves
        child.setBorderWidth(this.width + margin * 2);
        child.setBorderHeight(this.height + margin * 2);

        children.add(child);
    }

    public List<Shape> getInnerShapes() {
        return children;
    }

    @Override
    public void move() {
        super.move();
        for (Shape child : children) {
            child.move();
        }
    }

    @Override
    protected void drawShape(Painter painter) {
        // has to be fillRect, otherwise testing won't pass
        painter.fillRect(this.colour, this.x, this.y, this.width, this.height);

        // 2. For each child, translate xy to parent's xy - margin
        painter.translate(this.x - margin,this.y - margin);

        for (Shape child : children) {
            child.draw(painter);
        }
        painter.translate(-(this.x - margin), -(this.y - margin));
    }

    @Override
    public double getDistanceToPoint(int pX, int pY) {
        double minDistance = super.getDistanceToPoint(pX, pY);

        for (Shape child : children) {
            // Adjust the point relative to the nested coordinate space
            double childDistance = child.getDistanceToPoint(pX - (this.x - margin), pY - (this.y - margin));
            minDistance = Math.min(minDistance, childDistance);
        }

        return minDistance;
    }

    // For blinkingShape logic
    @Override
    public Colour getColour() {
        if (!children.isEmpty()) {
            return children.get(0).getColour();
        }
        return this.colour;
    }
}
