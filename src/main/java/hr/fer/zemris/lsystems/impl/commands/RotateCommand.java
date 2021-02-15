package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Model of rotate command
 */
public class RotateCommand implements Command {

    private double angle;

    /**
     * Constructs rotate command
     *
     * @param angle
     */
    public RotateCommand(double angle) {
        this.angle = angle;
    }

    /**
     * Modifies direction of turtle in current state
     *
     * @param ctx context of turtle's state
     * @param painter draws certain task
     */
    @Override
    public void execute(Context ctx, Painter painter) {
        TurtleState current = ctx.getCurrentState();
        current.setCurrentAngle(current.getCurrentAngle().rotated(angle * (Math.PI / 180)));
    }
}
