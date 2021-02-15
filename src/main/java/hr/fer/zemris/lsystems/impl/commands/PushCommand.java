package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Model of push command
 */
public class PushCommand implements Command {


    /**
     * Copies state from top of stack and pushes it onto top of stack
     *
     * @param ctx context of turtle's state
     * @param painter draws certain task
     */
    @Override
    public void execute(Context ctx, Painter painter) {
        TurtleState current = ctx.getCurrentState().copy();
        ctx.pushState(current);
    }
}
