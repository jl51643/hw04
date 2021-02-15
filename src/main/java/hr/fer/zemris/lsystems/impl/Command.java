package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * Contract for executing commands
 */
public interface Command {

    /**
     * Executes certain action for given context
     *
     * @param ctx context of turtle's state
     * @param painter draws certain task
     */
    void execute(Context ctx, Painter painter);
}
