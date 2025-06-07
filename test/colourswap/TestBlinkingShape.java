package colourswap;

import colourswap.model.Colour;
import colourswap.model.Config;
import colourswap.model.shapes.BlinkingShape;
import colourswap.model.shapes.Player;
import colourswap.model.shapes.Shape;
import colourswap.utils.CollisionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestBlinkingShape {

    private Shape shape;
    private MockPainter painter;

    @BeforeEach
    public void setUp() {
        // TODO Increment I Task One. Uncomment this line after implementing BlinkingShape class
        this.shape = new BlinkingShape(30, 30, Colour.RED, 50, 20);
        this.painter = new MockPainter();
    }

    @Test
    public void testMovement() {
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillRect 33 33 50 20 RED\n", this.painter.output());
    }

    @Test
    public void testBounceOnLeftEdge() {
        this.shape.setX(5);
        this.shape.setDx(-5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillRect 0 33 50 20 RED\nfillRect 5 36 50 20 RED\n", this.painter.output());
    }

    @Test
    public void testBounceOnRightEdge() {
        this.shape.setX(545);
        this.shape.setDx(5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillRect 550 33 50 20 RED\nfillRect 545 36 50 20 RED\n", this.painter.output());
    }

    @Test
    public void testBounceOnTopEdge() {
        this.shape.setY(5);
        this.shape.setDy(-5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillRect 33 0 50 20 RED\nfillRect 36 5 50 20 RED\n", this.painter.output());
    }

    @Test
    public void testBounceOnBottomEdge() {
        this.shape.setY(575);
        this.shape.setDy(5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals("fillRect 33 580 50 20 RED\nfillRect 36 575 50 20 RED\n", this.painter.output());
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
    // TODO Increment I Task One. Complete this test
    public void testColourChanges() {
        Colour initial = shape.getColour();

        // Simulate enough game frames to trigger the blink
        for (int i = 0; i < Config.SHAPE_BLINK_DELAY; i++) {
            shape.move();  // move also triggers colour tick
        }

        Colour after = shape.getColour();

        assertNotEquals(initial, after, "BlinkingShape should change colour after ");
    }
}
