package hr.fer.zemris.lsystems.impl;

/**
 * Model of context which enable us to manipulate with active states of turtle
 */
public class Context {

    /**
     * Stack of active states of turtle, state on top of stack is current state
     */
    private ObjectStack<TurtleState> stack;

    /**
     * Constructs new context with empty stack
     */
    public Context() {
        this.stack = new ObjectStack<>();
    }

    /**
     * @return returns current state
     */
    public TurtleState getCurrentState() {
        return stack.peek();
    }

    /**
     * Pushes new active state on top of stack of states
     *
     * @param state current active state to push on top of stack of states
     */
    public void pushState(TurtleState state) {
        stack.push(state);
    }

    /**
     * Pops last active state from top of stack of states
     */
    public void popState() {
        stack.pop();
    }
}
