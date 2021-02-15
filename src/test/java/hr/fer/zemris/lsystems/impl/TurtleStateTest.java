package hr.fer.zemris.lsystems.impl;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TurtleStateTest {

    @Test
    public void copyTest() {
        TurtleState t = new TurtleState(new Vector2D(3,4), new Vector2D(0.866, 0.5), Color.MAGENTA, 5);
        TurtleState s = t.copy();
        s.setColor(Color.LIGHT_GRAY);
        assertNotEquals(t.getColor(), s.getColor());
    }
}