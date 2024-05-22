package com.example;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Rental {
    private static int counter = 0;
    private String rentalId;
    private Bike bike;
    private User user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double charges;

    public Rental(User user, Bike bike, int duration) {
        this.rentalId = "R" + (++counter);
        this.user = user;
        this.bike = bike;
        this.startTime = LocalDateTime.now();
        this.endTime = startTime.plusHours(duration);
        this.charges = calculateCharges();
    }

    // Getters and setters
    public String getRentalId() {
        return rentalId;
    }

    public Bike getBike() {
        return bike;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public double getCharges() {
        return charges;
    }

    public boolean isOngoing() {
        return LocalDateTime.now().isBefore(endTime);
    }

    public void endRental() {
        this.endTime = LocalDateTime.now();
        this.charges = calculateCharges();
    }

    public double calculateCharges() {
        long hours = ChronoUnit.HOURS.between(startTime, endTime);
        return hours * 10.0; // Assume $10 per hour
    }

    public String generateInvoice() {
        return "Invoice: \nRental ID: " + rentalId + "\nBike: " + bike + "\nUser: " + user.getUsername() +
                "\nStart Time: " + startTime + "\nEnd Time: " + endTime + "\nCharges: $" + charges;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId='" + rentalId + '\'' +
                ", bike=" + bike +
                ", user=" + user.getUsername() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", charges=" + charges +
                '}';
    }
}
