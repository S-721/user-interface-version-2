package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeRentalSystem {
    private List<User> users;
    private List<Bike> bikes;

    public BikeRentalSystem() {
        this.users = new ArrayList<>();
        this.bikes = new ArrayList<>();
    }

    public void registerUser(String username, String password, String contactDetails) {
        users.add(new User(username, password, contactDetails));
        System.out.println("User registered: " + username);
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("User authenticated: " + username);
                return user;
            }
        }
        System.out.println("Authentication failed for: " + username);
        return null;
    }

    public void addBike(String bikeId, String model, String condition) {
        bikes.add(new Bike(bikeId, model, condition));
        System.out.println("Bike added: " + bikeId);
    }

    public void updateBike(String bikeId, String model, String condition) {
        for (Bike bike : bikes) {
            if (bike.getBikeId().equals(bikeId)) {
                bike.updateDetails(model, condition);
                System.out.println("Bike updated: " + bikeId);
                return;
            }
        }
        System.out.println("Bike not found: " + bikeId);
    }

    public void removeBike(String bikeId) {
        bikes.removeIf(bike -> bike.getBikeId().equals(bikeId));
        System.out.println("Bike removed: " + bikeId);
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public static void main(String[] args) {
        BikeRentalSystem system = new BikeRentalSystem();
        Scanner scanner = new Scanner(System.in);

        // Sample data for demonstration
        system.addBike("B1", "Mountain Bike", "New");
        system.addBike("B2", "Road Bike", "Good");

        while (true) {
            System.out.println("1. Register User");
            System.out.println("2. Authenticate User");
            System.out.println("3. Add Bike");
            System.out.println("4. Update Bike");
            System.out.println("5. Remove Bike");
            System.out.println("6. Browse Bikes");
            System.out.println("7. Rent Bike");
            System.out.println("8. Return Bike");
            System.out.println("9. View Rental History");
            System.out.println("10. View Rental Charges");
            System.out.println("11. Generate Rental Invoice");
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter contact details: ");
                    String contactDetails = scanner.nextLine();
                    system.registerUser(username, password, contactDetails);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String authUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String authPassword = scanner.nextLine();
                    User user = system.authenticateUser(authUsername, authPassword);
                    if (user != null) {
                        boolean isAuthenticated = true;
                        while (isAuthenticated) {
                            System.out.println("1. Browse Available Bikes");
                            System.out.println("2. Search for Bikes");
                            System.out.println("3. Rent Bike");
                            System.out.println("4. Return Bike");
                            System.out.println("5. View Rental History");
                            System.out.println("6. Manage Profile");
                            System.out.println("7. View Rental Charges");
                            System.out.println("8. Generate Rental Invoice");
                            System.out.println("9. Logout");
                            System.out.print("Choose an option: ");
                            int userOption = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (userOption) {
                                case 1:
                                    user.browseAvailableBikes(system.getBikes());
                                    break;
                                case 2:
                                    System.out.print("Enter search criteria: ");
                                    String criteria = scanner.nextLine();
                                    user.searchForBikes(system.getBikes(), criteria);
                                    break;
                                case 3:
                                    System.out.print("Enter bike ID to rent: ");
                                    String bikeId = scanner.nextLine();
                                    System.out.print("Enter rental duration (hours): ");
                                    int duration = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    for (Bike bike : system.getBikes()) {
                                        if (bike.getBikeId().equals(bikeId)) {
                                            user.rentBike(bike, duration);
                                            break;
                                        }
                                    }
                                    break;
                                case 4:
                                    System.out.print("Enter bike ID to return: ");
                                    bikeId = scanner.nextLine();
                                    for (Bike bike : system.getBikes()) {
                                        if (bike.getBikeId().equals(bikeId)) {
                                            user.returnBike(bike);
                                            break;
                                        }
                                    }
                                    break;
                                case 5:
                                    user.viewRentalHistory();
                                    break;
                                case 6:
                                    System.out.print("Enter new contact details: ");
                                    String newDetails = scanner.nextLine();
                                    user.manageProfile(newDetails);
                                    break;
                                case 7:
                                    System.out.print("Enter bike ID to view charges: ");
                                    bikeId = scanner.nextLine();
                                    for (Bike bike : system.getBikes()) {
                                        if (bike.getBikeId().equals(bikeId)) {
                                            user.viewRentalCharges(bike);
                                            break;
                                        }
                                    }
                                    break;
                                case 8:
                                    System.out.print("Enter bike ID to generate invoice: ");
                                    bikeId = scanner.nextLine();
                                    for (Bike bike : system.getBikes()) {
                                        if (bike.getBikeId().equals(bikeId)) {
                                            user.generateRentalInvoice(bike);
                                            break;
                                        }
                                    }
                                    break;
                                case 9:
                                    isAuthenticated = false;
                                    System.out.println("User logged out.");
                                    break;
                                default:
                                    System.out.println("Invalid option. Try again.");
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter bike ID: ");
                    String bikeId = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter condition: ");
                    String condition = scanner.nextLine();
                    system.addBike(bikeId, model, condition);
                    break;
                case 4:
                    System.out.print("Enter bike ID: ");
                    bikeId = scanner.nextLine();
                    System.out.print("Enter new model: ");
                    model = scanner.nextLine();
                    System.out.print("Enter new condition: ");
                    condition = scanner.nextLine();
                    system.updateBike(bikeId, model, condition);
                    break;
                case 5:
                    System.out.print("Enter bike ID: ");
                    bikeId = scanner.nextLine();
                    system.removeBike(bikeId);
                    break;
                case 6:
                    for (Bike bike : system.getBikes()) {
                        System.out.println(bike);
                    }
                    break;
                case 12:
                    scanner.close();
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

