package colourswap;

import colourswap.model.Colour;
import colourswap.model.shapes.RectangleShape;
import colourswap.model.shapes.RobotPlayer;
import colourswap.model.shapes.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRobotPlayer {

    private MockPainter painter;
    private RobotPlayer player;

    @BeforeEach
    public void setUp() {
        this.painter = new MockPainter();
        this.player = new RobotPlayer(270, 270, Colour.BLUE, 30);
    }

    @Test
    public void testOneNearbyShape() {
        List<Shape> shapes = List.of(new RectangleShape(200, 280, Colour.RED, 10, 10));
        this.player.decideMovement(shapes);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 275 275 60 60 BLUE\n", this.painter.output());
    }

    @Test
    public void testOneFarawayShape() {
        List<Shape> shapes = List.of(new RectangleShape(150, 450, Colour.RED, 10, 10));
        this.player.decideMovement(shapes);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 270 270 60 60 BLUE\n", this.painter.output());
    }

    @Test
    public void testMultipleNearbyShapes() {
        List<Shape> shapes = List.of(
                new RectangleShape(200, 280, Colour.RED, 10, 10),
                new RectangleShape(350, 280, Colour.RED, 10, 10));
        this.player.decideMovement(shapes);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 265 275 60 60 BLUE\n", this.painter.output());
    }

    @Test
    public void testRunningAgainstEdge() {
        this.player.setX(0);
        List<Shape> shapes = List.of(new RectangleShape(70, 250, Colour.RED, 10, 10));
        this.player.decideMovement(shapes);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 0 275 60 60 BLUE\n", this.painter.output());
    }

    @Test
    public void testRunningAgainstCorner() {
        this.player.setX(0);
        this.player.setY(0);
        List<Shape> shapes = List.of(new RectangleShape(70, 70, Colour.RED, 10, 10));
        this.player.decideMovement(shapes);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 0 0 60 60 BLUE\n", this.painter.output());
    }

    @Test
    public void testSameColourShapesIgnored() {
        List<Shape> shapes = List.of(new RectangleShape(250, 250, Colour.BLUE, 10, 10));
        this.player.decideMovement(shapes);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 270 270 60 60 BLUE\n", this.painter.output());
    }

    @Test
    public void testReturnToMiddle() {
        this.player.setX(0);
        this.player.setY(0);
        List<Shape> shapes = Collections.emptyList();
        this.player.decideMovement(shapes);
        this.player.move();
        this.player.draw(this.painter);
        assertEquals("fillOval 5 5 60 60 BLUE\n", this.painter.output());
    }

}