package colourswap;

import colourswap.model.Colour;
import colourswap.model.shapes.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {

    private Player player;
    private MockPainter painter;

    @BeforeEach
    public void setUp() {
        this.player = new Player(20, 20, Colour.RED, 30);
        this.painter = new MockPainter();
    }

    @Test
    public void testMoveLeft() {
        this.player.startMovingLeft();
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        this.player.stopMovingLeft();
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 15 20 60 60 RED\nfillOval 10 20 60 60 RED\nfillOval 10 20 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testMoveRight() {
        this.player.startMovingRight();
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        this.player.stopMovingRight();
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 25 20 60 60 RED\nfillOval 30 20 60 60 RED\nfillOval 30 20 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testMoveUp() {
        this.player.startMovingUp();
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        this.player.stopMovingUp();
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 20 15 60 60 RED\nfillOval 20 10 60 60 RED\nfillOval 20 10 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testMoveDown() {
        this.player.startMovingDown();
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        this.player.stopMovingDown();
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 20 25 60 60 RED\nfillOval 20 30 60 60 RED\nfillOval 20 30 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testStopAtLeftEdge() {
        this.player.setX(10);
        this.player.startMovingLeft();
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 5 20 60 60 RED\nfillOval 0 20 60 60 RED\nfillOval 0 20 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testStopAtRightEdge() {
        this.player.setX(530);
        this.player.startMovingRight();
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 535 20 60 60 RED\nfillOval 540 20 60 60 RED\nfillOval 540 20 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testStopAtTopEdge() {
        this.player.setY(10);
        this.player.startMovingUp();
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 20 5 60 60 RED\nfillOval 20 0 60 60 RED\nfillOval 20 0 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testStopAtBottomEdge() {
        this.player.setY(530);
        this.player.startMovingDown();
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 20 535 60 60 RED\nfillOval 20 540 60 60 RED\nfillOval 20 540 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testMoveLeftAndRight() {
        this.player.startMovingLeft();
        this.player.startMovingRight();
        this.player.move();
        this.player.draw(this.painter);
        this.player.stopMovingLeft();
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 20 20 60 60 RED\nfillOval 25 20 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testMoveLeftAndUp() {
        this.player.startMovingLeft();
        this.player.startMovingUp();
        this.player.move();
        this.player.draw(this.painter);
        this.player.stopMovingLeft();
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 15 15 60 60 RED\nfillOval 15 10 60 60 RED\n", this.painter.output());
    }

    @Test
    public void testMoveLeftRightAndUp() {
        this.player.startMovingLeft();
        this.player.startMovingRight();
        this.player.startMovingUp();
        this.player.move();
        this.player.draw(this.painter);
        this.player.stopMovingLeft();
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 20 15 60 60 RED\nfillOval 25 10 60 60 RED\n", this.painter.output());
    }

}
