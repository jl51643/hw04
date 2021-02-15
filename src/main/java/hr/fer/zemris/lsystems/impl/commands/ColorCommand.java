package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.Painter;
import java.awt.*;

/**
 * Model of color command
 */
public class ColorCommand implements Command {

    /**
     * Color of drawing
     */
    private Color color;

    /**
     * Constructs new color command
     *
     * @param color color of drawing
     */
    public ColorCommand(Color color) {
        this.color = color;
    }

    /**
     * Changes color of drawing of current state
     *
     * @param ctx context of turtle's state
     * @param painter draws certain task
     */
    @Override
    public void execute(Context ctx, Painter painter) {
        ctx.getCurrentState().setColor(color);
    }
}
