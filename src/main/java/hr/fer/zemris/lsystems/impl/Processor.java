package hr.fer.zemris.lsystems.impl;

/**
 * Model of an object capable of performing some operation on the passed object.
 */
public interface Processor<T> {

    /**
     * Performs selected operation.
     *
     * @param value Object over which process will be executed.
     */
    void process(T value);
}
