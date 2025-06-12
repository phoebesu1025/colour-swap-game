package colourswap;

import colourswap.model.Colour;
import colourswap.model.shapes.NestedShape;
import colourswap.model.shapes.Player;
import colourswap.model.shapes.RectangleShape;
import colourswap.model.shapes.Shape;
import colourswap.utils.CollisionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestNestedShape {
    // TODO Increment I Task Three. Uncomment the following after implementing NestedShape class

    private NestedShape shape;
    private MockPainter painter;

    @BeforeEach
    public void setUp() {
        this.shape = new NestedShape(30, 30, Colour.RED, 50, 20);
        this.shape.addInnerShape(new RectangleShape(0, 0, Colour.RED, 20, 20));
        this.painter = new MockPainter();
    }

    @Test
    public void testMovement() {
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillRect 33 33 50 20 RED\ntranslate 3 3\nfillRect 3 3 20 20 RED\ntranslate -3 -3\n", this.painter.output());
    }

    @Test
    public void testBounceOnLeftEdge() {
        this.shape.setX(5);
        this.shape.setDx(-5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals(
                "fillRect 0 33 50 20 RED\ntranslate -30 3\nfillRect 3 3 20 20 RED\ntranslate 30 -3\n"
                        + "fillRect 5 36 50 20 RED\ntranslate -25 6\nfillRect 6 6 20 20 RED\ntranslate 25 -6\n",
                this.painter.output());
    }

    @Test
    public void testBounceOnRightEdge() {
        this.shape.setX(545);
        this.shape.setDx(5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals(
                "fillRect 550 33 50 20 RED\ntranslate 520 3\nfillRect 3 3 20 20 RED\ntranslate -520 -3\n"
                        + "fillRect 545 36 50 20 RED\ntranslate 515 6\nfillRect 6 6 20 20 RED\ntranslate -515 -6\n",
                this.painter.output());
    }

    @Test
    public void testBounceOnTopEdge() {
        this.shape.setY(5);
        this.shape.setDy(-5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals(
                "fillRect 33 0 50 20 RED\ntranslate 3 -30\nfillRect 3 3 20 20 RED\ntranslate -3 30\n"
                        + "fillRect 36 5 50 20 RED\ntranslate 6 -25\nfillRect 6 6 20 20 RED\ntranslate -6 25\n",
                this.painter.output());
    }

    @Test
    public void testBounceOnBottomEdge() {
        this.shape.setY(575);
        this.shape.setDy(5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals(
                "fillRect 33 580 50 20 RED\ntranslate 3 550\nfillRect 3 3 20 20 RED\ntranslate -3 -550\n"
                        + "fillRect 36 575 50 20 RED\ntranslate 6 545\nfillRect 6 6 20 20 RED\ntranslate -6 -545\n",
                this.painter.output());
    }

    @Test
    public void testDistanceToPlayer() {
        assertEquals(30.0, this.shape.getDistanceToPoint(80, 80));
    }

    @Test
    public void testTouchingPlayer() {
        Player player = new Player(50, 50, Colour.RED, 30);
        assertTrue(CollisionUtils.isColliding(this.shape, player));
    }

    @Test
    public void testInnerShapeTouchingPlayer() {
        Player player = new Player(50, 51, Colour.RED, 30);
        assertFalse(CollisionUtils.isColliding(this.shape, player));
        Shape innerShape = this.shape.getInnerShapes().get(0);
        innerShape.setX(50);
        innerShape.setY(50);
        assertTrue(CollisionUtils.isColliding(this.shape, player));
    }

}
