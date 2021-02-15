package hr.fer.zemris.lsystems.impl;

/**
 * Model of object that tests if some given object is acceptable for some action.
 */
public interface Tester<T> {

    /**
     * Returns true if given object is acceptable, false otherwise.
     *
     * @param obj object to test.
     * @return returns true if given object is acceptable, false otherwise.
     */
    boolean test(T obj);
}
