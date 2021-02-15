package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.lsystems.impl.Vector2D;

/**
 * Model of draw command
 */
public class DrawCommand implements Command {

    private double step;

    /**
     * Constructs draw command
     *
     * @param step length of line to draw
     */
    public DrawCommand(double step) {
        this.step = step;
    }

    /**
     * Draws line of given step and color of current state
     *
     * @param ctx context of turtle's state
     * @param painter draws certain task
     */
    @Override
    public void execute(Context ctx, Painter painter) {
        TurtleState current = ctx.getCurrentState();
        double angle = Math.atan2(current.getCurrentAngle().getY(), current.getCurrentAngle().getX());
        double newX = current.getCurrentPosition().getX() + step * Math.cos(angle) * current.getCurrentOffset();
        double newY = current.getCurrentPosition().getY() + step * Math.sin(angle) * current.getCurrentOffset();

        painter.drawLine(current.getCurrentPosition().getX(),
                current.getCurrentPosition().getY(),
                newX,
                newY,
                current.getColor(),
                1f);

        current.setCurrentPosition(new Vector2D(newX, newY));
    }
}
