package com.github.zachmassia.fincher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FinchControllerTest {

    @Test
    public void testMovementSpeed() throws Exception {
        FinchController f = new FinchController();

        System.out.println("setMovementSpeed should accept a valid speed.");
        int validSpeed = 1;
        f.setMovementSpeed(validSpeed);
        assertEquals(validSpeed, f.getMovementSpeed());

        System.out.println("setMovementSpeed should cap any speed >255 to 255.");
        int invalidSpeedHigh = 256;
        f.setMovementSpeed(invalidSpeedHigh);
        assertEquals(255, f.getMovementSpeed());

        System.out.println("setMovementSpeed should set any negative to 0.");
        int invalidSpeedLow = -1;
        f.setMovementSpeed(invalidSpeedLow);
        assertEquals(0, f.getMovementSpeed());
    }

    @Test
    public void testMovementTime() throws Exception {
        FinchController f = new FinchController();

        System.out.println("getMovementTime should return the value passed to setMovementTime.");
        int time = 1000;
        f.setMovementTime(time);
        assertEquals(time, f.getMovementTime());
    }
}
