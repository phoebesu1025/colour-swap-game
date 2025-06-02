package colourswap;

import colourswap.model.Colour;
import colourswap.model.shapes.CircleShape;
import colourswap.model.shapes.Player;
import colourswap.model.shapes.Shape;
import colourswap.utils.CollisionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestCircleShape {

    private Shape shape;
    private MockPainter painter;

    @BeforeEach
    public void setUp() {
        this.shape = new CircleShape(30, 30, Colour.RED, 20);
        this.painter = new MockPainter();
    }

    @Test
    public void testMovement() {
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillOval 33 33 40 40 RED\n", this.painter.output());
    }

    @Test
    public void testBounceOnLeftEdge() {
        this.shape.setX(5);
        this.shape.setDx(-5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillOval 0 33 40 40 RED\nfillOval 5 36 40 40 RED\n", this.painter.output());
    }

    @Test
    public void testBounceOnRightEdge() {
        this.shape.setX(555);
        this.shape.setDx(5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillOval 560 33 40 40 RED\nfillOval 555 36 40 40 RED\n", this.painter.output());
    }

    @Test
    public void testBounceOnTopEdge() {
        this.shape.setY(5);
        this.shape.setDy(-5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillOval 33 0 40 40 RED\nfillOval 36 5 40 40 RED\n", this.painter.output());
    }

    @Test
    public void testBounceOnBottomEdge() {
        this.shape.setY(555);
        this.shape.setDy(5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillOval 33 560 40 40 RED\nfillOval 36 555 40 40 RED\n", this.painter.output());
    }

    @Test
    public void testDistanceToPlayer() {
        assertEquals(30.0, this.shape.getDistanceToPoint(80, 90));
    }

    @Test
    public void testTouchingPlayer() {
        Player player = new Player(50, 60, Colour.RED, 30);
        assertTrue(CollisionUtils.isColliding(this.shape, player));
    }

}