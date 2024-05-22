package com.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String contactDetails;
    private List<Rental> rentalHistory;

    public User(String username, String password, String contactDetails) {
        this.username = username;
        this.password = password;
        this.contactDetails = contactDetails;
        this.rentalHistory = new ArrayList<>();
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public List<Rental> getRentalHistory() {
        return rentalHistory;
    }

    public void register() {
        // Registration logic (handled by BikeRentalSystem)
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void browseAvailableBikes(List<Bike> bikes) {
        for (Bike bike : bikes) {
            if (bike.isAvailable()) {
                System.out.println(bike);
            }
        }
    }

    public void searchForBikes(List<Bike> bikes, String criteria) {
        for (Bike bike : bikes) {
            if (bike.matchesCriteria(criteria)) {
                System.out.println(bike);
            }
        }
    }

    public void rentBike(Bike bike, int duration) {
        if (bike.isAvailable()) {
            Rental rental = new Rental(this, bike, duration);
            rentalHistory.add(rental);
            bike.setAvailable(false);
            System.out.println("Bike rented: " + bike);
        } else {
            System.out.println("Bike is not available.");
        }
    }

    public void returnBike(Bike bike) {
        for (Rental rental : rentalHistory) {
            if (rental.getBike().equals(bike) && rental.isOngoing()) {
                rental.endRental();
                bike.setAvailable(true);
                System.out.println("Bike returned: " + bike);
                return;
            }
        }
        System.out.println("No ongoing rental found for this bike.");
    }

    public void viewRentalHistory() {
        for (Rental rental : rentalHistory) {
            System.out.println(rental);
        }
    }

    public void manageProfile(String newDetails) {
        this.contactDetails = newDetails;
    }

    public void viewRentalCharges(Bike bike) {
        for (Rental rental : rentalHistory) {
            if (rental.getBike().equals(bike)) {
                System.out.println("Rental charges: " + rental.calculateCharges());
                return;
            }
        }
        System.out.println("No rental found for this bike.");
    }

    public void generateRentalInvoice(Bike bike) {
        for (Rental rental : rentalHistory) {
            if (rental.getBike().equals(bike)) {
                System.out.println("Rental invoice: " + rental.generateInvoice());
                return;
            }
        }
        System.out.println("No rental found for this bike.");
    }
}
