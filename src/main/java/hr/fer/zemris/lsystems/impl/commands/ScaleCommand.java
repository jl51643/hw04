package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Model of scale command
 */
public class ScaleCommand implements Command {

    /**
     * Factor of scaling
     */
    private double factor;

    /**
     * Constructs new scale command
     *
     * @param factor factor of scaling
     */
    public ScaleCommand(double factor) {
        this.factor = factor;
    }

    /**
     * Scales current effective offset
     *
     * @param ctx context of turtle's state
     * @param painter draws certain task
     */
    @Override
    public void execute(Context ctx, Painter painter) {
        TurtleState current = ctx.getCurrentState();
        current.setCurrentOffset(current.getCurrentOffset() * factor);
    }
}
