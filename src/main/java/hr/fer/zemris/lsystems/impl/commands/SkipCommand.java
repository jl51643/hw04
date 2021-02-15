package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.lsystems.impl.Vector2D;

/**
 * Model of skip command
 */
public class SkipCommand implements Command {

    /**
     * Next step of turtle
     */
    private double step;

    /**
     * Constructs new skip command
     *
     * @param step next step of command
     */
    public SkipCommand(double step) {
        this.step = step;
    }

    /**
     * Skips to next position without drawing line
     *
     * @param ctx context of turtle's state
     * @param painter draws certain task
     */
    @Override
    public void execute(Context ctx, Painter painter) {
        TurtleState current = ctx.getCurrentState();
        double angle = Math.atan2(current.getCurrentAngle().getY(), current.getCurrentAngle().getX());
        double newX = current.getCurrentPosition().getX() + step * Math.cos(angle);
        double newY = current.getCurrentPosition().getY() + step * Math.sin(angle);
        current.setCurrentPosition(new Vector2D(newX, newY));
    }
}
