package com.example;

public class Bike {
    private String bikeId;
    private String model;
    private String condition;
    private boolean available;

    public Bike(String bikeId, String model, String condition) {
        this.bikeId = bikeId;
        this.model = model;
        this.condition = condition;
        this.available = true;
    }

    // Getters and setters
    public String getBikeId() {
        return bikeId;
    }

    public String getModel() {
        return model;
    }

    public String getCondition() {
        return condition;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void updateDetails(String model, String condition) {
        this.model = model;
        this.condition = condition;
    }

    public boolean matchesCriteria(String criteria) {
        return model.contains(criteria) || condition.contains(criteria);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "bikeId='" + bikeId + '\'' +
                ", model='" + model + '\'' +
                ", condition='" + condition + '\'' +
                ", available=" + available +
                '}';
    }
}
