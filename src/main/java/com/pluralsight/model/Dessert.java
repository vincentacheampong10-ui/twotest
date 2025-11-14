package com.pluralsight.model;

import java.util.Scanner;

public class Dessert {
    private String name;
    private double price;

    public Dessert(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static Dessert createFromUserInput(Scanner scanner) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║             🍰  CHOOSE A DESSERT             ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1️⃣  Cake ....................... GHS 8       ║");
        System.out.println("║ 2️⃣  Chin Chin .................. GHS 6       ║");
        System.out.println("║ 3️⃣  Fruit Cup .................. GHS 7       ║");
        System.out.println("║ 4️⃣  None ....................... GHS 0       ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Enter choice:");
        switch (scanner.nextLine()) {
            case "1":
                return new Dessert("Cake", 8);
            case "2":
                return new Dessert("Chin Chin", 6);
            case "3":
                return new Dessert("Fruit Cup", 7);
            case "4":
                return new Dessert("None", 0);
            default:
                System.out.println("Invalid selection. Select the above options");
                return null;
        }
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (GHS " + price + ")";
    }
}
