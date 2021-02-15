package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.lsystems.impl.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommandsTest {

    Context context;
    Painter painter;

    @BeforeEach
    public void setUp() {
        context = new Context();
        TurtleState t1 = new TurtleState(new Vector2D(3,4), new Vector2D(0.866, 0.5), Color.MAGENTA, 5);
        TurtleState t2 = new TurtleState(new Vector2D(7,8), new Vector2D(0.707, 0.707), Color.BLACK, 4);
        TurtleState t3 = new TurtleState(new Vector2D(5,3), new Vector2D(0.707, -0.707), Color.WHITE, 3);
        TurtleState t4 = new TurtleState(new Vector2D(3,1), new Vector2D(0.0, 1), Color.RED, 7);
        TurtleState t5 = new TurtleState(new Vector2D(1,2), new Vector2D(0.0, -1), Color.BLUE, 8);
        TurtleState t6 = new TurtleState(new Vector2D(2,5), new Vector2D(0.5, 0.866), Color.GREEN, 2);
        context.pushState(t1);
        context.pushState(t2);
        context.pushState(t3);
        context.pushState(t4);
        context.pushState(t5);
        context.pushState(t6);
    }

    @Test
    public void testPopCommand() {
        PopCommand pop = new PopCommand();
        pop.execute(context, painter);
        assertEquals(1, context.getCurrentState().getCurrentPosition().getX());
    }

    @Test
    public void tetPushCommand() {
        PushCommand push = new PushCommand();
        push.execute(context, painter);
        context.getCurrentState().setColor(Color.ORANGE);
        TurtleState t = context.getCurrentState();
        context.popState();
        assertNotEquals(t, context.getCurrentState());
    }

    @Test
    public void testRotateCommand() {
        RotateCommand r = new RotateCommand(30);
        System.out.println(context.getCurrentState().getCurrentAngle().getX());
        System.out.println(context.getCurrentState().getCurrentAngle().getY());
        r.execute(context, painter);
        System.out.println(context.getCurrentState().getCurrentAngle().getX());
        System.out.println(context.getCurrentState().getCurrentAngle().getY());

        assertTrue(Math.abs(1 - context.getCurrentState().getCurrentAngle().getY()) < 1E-4);
        assertTrue(Math.abs(0 - context.getCurrentState().getCurrentAngle().getX()) < 1E-4);
    }

    @Test
    public void testDrawCommand() {
        DrawCommand d = new DrawCommand(5);
        d.execute(context, painter);
    }

    @Test
    public void testSkipCommand() {
        SkipCommand s = new SkipCommand(5);
        System.out.println(context.getCurrentState().getCurrentPosition().getX() + " " + context.getCurrentState().getCurrentPosition().getY());
        s.execute(context, painter);
        System.out.println(context.getCurrentState().getCurrentPosition().getX() + " " + context.getCurrentState().getCurrentPosition().getY());
    }

    @Test
    public void testScaleCommand() {
        ScaleCommand s = new ScaleCommand(4);
        s.execute(context, painter);
        assertTrue(Math.abs(8 - context.getCurrentState().getCurrentOffset()) < 1E-10);
    }

    @Test
    public void testColorCommand() {
        ColorCommand c = new ColorCommand(Color.CYAN);
        c.execute(context, painter);
        assertEquals(Color.CYAN, context.getCurrentState().getColor());
    }
}

















