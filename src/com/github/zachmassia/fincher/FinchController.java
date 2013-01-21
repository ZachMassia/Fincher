package com.github.zachmassia.fincher;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import java.awt.*;

/**
 * A light wrapper around the base Finch class.
 * Provides human readable movement methods, and allows the user to
 * store the motor speed and move durations.
 */
public class FinchController {

    public Finch finch;

    // Config values
    private Color ledColor;
    private int movementSpeed;
    private int movementTime = 250;

    /**
     * Open the connection to the Finch robot.
     */
    public void connectToFinch() {
        finch = new Finch();
    }

    /**
     * Move the Finch forward.
     * Uses the previously set speeds and duration.
     */
    public void moveUp() {
        finch.setWheelVelocities(movementSpeed, movementSpeed, movementTime);
    }

    /**
     * Move the Finch backwards.
     * Uses the previously set speeds and duration.
     */
    public void moveDown() {
        finch.setWheelVelocities(-movementSpeed, -movementSpeed, movementTime);
    }

    /**
     * Move the Finch left while still going forward.
     * Uses the previously set speeds and duration.
     */
    public void smoothLeft() {
        finch.setWheelVelocities((movementSpeed / 2), movementSpeed, movementTime);
    }

    /**
     * Move the Finch right while still going forward.
     * Uses the previously set speeds and duration.
     */
    public void smoothRight() {
        finch.setWheelVelocities(movementSpeed, (movementSpeed / 2), movementTime);
    }

    /**
     * Pivot the Finch on the spot, towards the left.
     * Uses the previously set speeds and duration.
     */
    public void pivotLeft() {
        finch.setWheelVelocities(-movementSpeed, movementSpeed, movementTime);
    }


    /**
     * Pivot the Finch on the spot, towards the right.
     * Uses the previously set speeds and duration.
     */
    public void pivotRight() {
        finch.setWheelVelocities(movementSpeed, -movementSpeed, movementTime);
    }

    /**
     * @return Color The current value of the Finch's LED.
     */
    public Color getLedColor() {
        return ledColor;
    }

    /**
     * Set the Finch's LED to the new color.
     * @param ledColor New color value
     */
    public void setLedColor(Color ledColor) {
        this.ledColor = ledColor;
        finch.setLED(ledColor);
    }

    /**
     * The absolute speed at which the motors are set to move.
     * @return Speed Motor speed (0-255)
     */
    public int getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Sets the Finch's movement speed. This value is negated when the
     * finch must move in reverse.
     * @param movementSpeed a 0-255 value at which to move the Finch
     */
    public void setMovementSpeed(int movementSpeed) {
        if (movementSpeed >= 0 && movementSpeed < 256) {
            // Valid range
            this.movementSpeed = movementSpeed;
        } else if (movementSpeed < 0) {
            // Negative, set speed to 0.
            this.movementSpeed = 0;
        } else {
            // >255, set to 255
            this.movementSpeed = 255;
        }
    }

    /**
     * The duration the motors are set to move.
     * @return Time in milliseconds
     */
    public int getMovementTime() {
        return movementTime;
    }

    /**
     * How long the motors are turned on. A smaller value will work
     * better if object avoidance is enabled.
     * @param movementTime Time in milliseconds to move bot
     */
    public void setMovementTime(int movementTime) {
        this.movementTime = movementTime;
    }
}
