package com.github.zachmassia.fincher;

/**
 * Finch robot diagnostics class to test basic functionality.
 * Provides a main method which tests the movement methods, queries each
 * sensor and prints output, and tests the buzzer.
 */
public class Diagnostics {

    public static void main(String args[]) {
        // Initialize the Finch
        FinchController bot = new FinchController();
        bot.connectToFinch();

        // Set the move values
        bot.setMovementTime(300);
        bot.setMovementSpeed(255);

        // Basic movement
        bot.moveUp();
        bot.moveDown();
        bot.pivotLeft();
        bot.pivotRight();

        // Smooth turns
        bot.setMovementTime(750);
        bot.setMovementSpeed(150);
        bot.smoothLeft();
        bot.smoothRight();

        toneTest(bot);
        printSensorDiagnostic(bot);

        bot.finch.quit();
    }

    /**
     * Print human readable output of all robot sensors.
     * @param bot the active controller class
     */
    private static void printSensorDiagnostic(FinchController bot) {
        System.out.println("Acceleration values: ");
        System.out.println("\tX: " + bot.finch.getXAcceleration());
        System.out.println("\tY: " + bot.finch.getYAcceleration());
        System.out.println("\tZ: " + bot.finch.getZAcceleration());

        System.out.println("Light values: ");
        System.out.println("\tLeft:  " + bot.finch.getLeftLightSensor());
        System.out.println("\tRight: " + bot.finch.getRightLightSensor());

        System.out.println("Finch is level:\n\t" + bot.finch.isFinchLevel());
        System.out.println("Obstacle:\n\t" + bot.finch.isObstacle());
        System.out.println("Temp:\n\t"+ (int)bot.finch.getTemperature() + "*C.");
    }

    /**
     * Play various frequencies on the Finch's internal buzzer.
     * @param bot the active controller class
     */
    private static void toneTest(FinchController bot) {
        System.out.println("Tone test..");
        bot.finch.buzzBlocking(1024, 150);
        bot.finch.buzzBlocking(1536, 150);
        bot.finch.buzzBlocking(2048, 300);
        bot.finch.buzzBlocking(1536, 200);
        bot.finch.buzzBlocking(2048, 400);
    }
}
