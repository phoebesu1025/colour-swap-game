package colourswap;

import colourswap.model.Colour;
import colourswap.model.shapes.Player;
import colourswap.model.shapes.Shape;
import colourswap.utils.CollisionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestTShape {

    private Shape shape;
    private MockPainter painter;

    @BeforeEach
    public void setUp() {
        // TODO Increment I Task Two. Uncomment this line after implementing TShape class
//        this.shape = new TShape(30, 30, Colour.RED, 60, 40);
        this.painter = new MockPainter();
    }

    @Test
    public void testMovement() {
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals(
                "translate 33 33\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate -33 -33\n",
                this.painter.output());
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
                "translate 0 33\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate 0 -33\n"
                        + "translate 5 36\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate -5 -36\n",
                this.painter.output());
    }

    @Test
    public void testBounceOnRightEdge() {
        this.shape.setX(535);
        this.shape.setDx(5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals(
                "translate 540 33\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate -540 -33\n"
                        + "translate 535 36\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate -535 -36\n",
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
                "translate 33 0\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate -33 0\n"
                        + "translate 36 5\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate -36 -5\n",
                this.painter.output());
    }

    @Test
    public void testBounceOnBottomEdge() {
        this.shape.setY(555);
        this.shape.setDy(5);
        this.shape.move();
        this.shape.draw(this.painter);
        this.shape.move();
        this.shape.draw(this.painter);
        assertEquals(
                "translate 33 560\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate -33 -560\n"
                        + "translate 36 555\nfillRect 0 0 20 20 RED\nfillRect 20 0 20 20 RED\nfillRect 40 0 20 20 RED\nfillRect 20 20 20 20 RED\ntranslate -36 -555\n",
                this.painter.output());
    }

    @Test
    public void testDistanceToPlayer() {
        assertEquals(30.0, this.shape.getDistanceToPoint(70, 100));
    }

    @Test
    public void testTouchingPlayer() {
        Player player = new Player(40, 70, Colour.RED, 30);
        assertTrue(CollisionUtils.isColliding(this.shape, player));
    }

    @Test
    // TODO Increment I Task Two. Complete this test
    public void testNotTouchingPlayerInHollowCorner() {
        fail();
    }

}
