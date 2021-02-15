package hr.fer.zemris.lsystems.impl;

import java.awt.*;

/**
 * Model of turtle state which saves current position of turtle
 */
public class TurtleState {

    /**
     * Vector of current position of turtle
     */
    private Vector2D currentPosition;

    /**
     * Unit vector of angle of turtle's direction
     */
    private Vector2D currentAngle;

    /**
     * Color of drawing
     */
    private Color color;

    /**
     * Current effective offset
     */
    private double currentOffset;

    /**
     * Constructs current turtle state
     *
     * @param currentPosition current position of turtle
     * @param currentAngle angle of current direction of turtle
     * @param color color of drawing
     * @param currentOffset current effective offset
     */
    public TurtleState(Vector2D currentPosition, Vector2D currentAngle, Color color, double currentOffset) {
        this.currentPosition = currentPosition;
        this.currentAngle = currentAngle;
        this.color = color;
        this.currentOffset = currentOffset;
    }

    /**
     * Returns new copy of current turtle state
     *
     * @return returns new copy of current turtle state
     */
    public TurtleState copy() {
        return new TurtleState(this.currentPosition, this.currentAngle, this.color, this.currentOffset);
    }

    /**
     * @return returns current position of turtle
     */
    public Vector2D getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Sets current position of turtle
     *
     * @param currentPosition new value for current position of turtle
     */
    public void setCurrentPosition(Vector2D currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * @return returns current direction of turtle
     */
    public Vector2D getCurrentAngle() {
        return currentAngle;
    }

    /**
     * Sets current direction of turtle
     *
     * @param currentAngle new value for direction of turtle
     */
    public void setCurrentAngle(Vector2D currentAngle) {
        this.currentAngle = currentAngle;
    }

    /**
     * @return returns current color of drawing
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color of drawing
     *
     * @param color new color for drawing
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return returns current effective offset
     */
    public double getCurrentOffset() {
        return currentOffset;
    }

    /**
     * Sets current effective offset
     *
     * @param currentOffset new value for current effective offset
     */
    public void setCurrentOffset(double currentOffset) {
        this.currentOffset = currentOffset;
    }
}
