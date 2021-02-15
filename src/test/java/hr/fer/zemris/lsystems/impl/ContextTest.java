package hr.fer.zemris.lsystems.impl;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ContextTest {

    @Test
    public void getCurrentStateTest() {
        Context c = new Context();
        c.pushState(new TurtleState(new Vector2D(3,4), new Vector2D(0.866, 0.5), Color.MAGENTA, 5));
        TurtleState s = c.getCurrentState();
        s.setColor(Color.BLUE);
        assertTrue(c.getCurrentState().getColor().equals(Color.BLUE));
    }

    @Test
    public void pushStateTest() {
        Context c = new Context();
        c.pushState(new TurtleState(new Vector2D(3,4), new Vector2D(0.866, 0.5), Color.MAGENTA, 5));
        TurtleState s = new TurtleState(new Vector2D(3,4), new Vector2D(0.866, 0.5), Color.MAGENTA, 5);
        assertEquals(s.getCurrentPosition().getX(), c.getCurrentState().getCurrentPosition().getX());
        assertEquals(s.getCurrentAngle().getY(), c.getCurrentState().getCurrentAngle().getY());
        assertTrue(Math.abs(s.getCurrentOffset() - c.getCurrentState().getCurrentOffset()) < 1E-10);
        assertEquals(s.getColor(), c.getCurrentState().getColor());

    }

    @Test
    public void popStateTest() {
        Context c = new Context();
        c.pushState(new TurtleState(new Vector2D(3,4), new Vector2D(0.866, 0.5), Color.MAGENTA, 5));
        c.popState();
        assertThrows(EmptyStackException.class, c::popState);
    }

}