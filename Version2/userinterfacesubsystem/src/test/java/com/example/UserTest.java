package com.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private BikeRentalSystem system;

    @Before
    public void setUp() {
        system = new BikeRentalSystem();
        system.addBike("B1", "Mountain Bike", "New");
        system.addBike("B2", "Road Bike", "Good");
    }

    @Test
    public void testRegisterUser() {
        system.registerUser("john_doe", "password123", "john@example.com");
        User user = system.authenticateUser("john_doe", "password123");
        assertNotNull("User should be registered and authenticated", user);
    }

    @Test
    public void testAuthenticateUser() {
        system.registerUser("jane_doe", "password456", "jane@example.com");
        User user = system.authenticateUser("jane_doe", "password456");
        assertNotNull("User should be authenticated", user);
    }

    @Test
    public void testRentBike() {
        system.registerUser("john_doe", "password123", "john@example.com");
        User user = system.authenticateUser("john_doe", "password123");
        Bike bike = system.getBikes().get(0);
        user.rentBike(bike, 2);
        assertFalse("Bike should be marked as not available after rental", bike.isAvailable());
    }

    @Test
    public void testReturnBike() {
        system.registerUser("john_doe", "password123", "john@example.com");
        User user = system.authenticateUser("john_doe", "password123");
        Bike bike = system.getBikes().get(0);
        user.rentBike(bike, 2);
        user.returnBike(bike);
        assertTrue("Bike should be marked as available after return", bike.isAvailable());
    }

    @Test
    public void testAddBike() {
        int initialBikeCount = system.getBikes().size();
        system.addBike("B3", "Hybrid Bike", "New");
        assertEquals("Bike count should increase by 1", initialBikeCount + 1, system.getBikes().size());
    }
}
