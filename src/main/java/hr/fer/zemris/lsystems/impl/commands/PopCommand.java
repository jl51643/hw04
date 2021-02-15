package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.Painter;

/**
 * Model of pop command
 */
public class PopCommand implements Command {

    /**
     * Pops one state from top of stack
     *
     * @param ctx context of turtle's state
     * @param painter draws certain task
     */
    @Override
    public void execute(Context ctx, Painter painter) {
        ctx.popState();
    }
}
