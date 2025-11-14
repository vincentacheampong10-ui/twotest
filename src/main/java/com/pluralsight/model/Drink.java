package com.pluralsight.model;

import java.util.Scanner;

public class Drink {
    private String name;
    private double price;

    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static Drink createFromUserInput(Scanner scanner) {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║                 CHOOSE A DRINK              ║");
        System.out.println("╠═════════════════════════════════════════════╣");
        System.out.println("║ 1️⃣  Sobolo .................... GHS 8       ║");
        System.out.println("║ 2️⃣  Malt ...................... GHS 10      ║");
        System.out.println("║ 3️⃣  Coke ...................... GHS 6       ║");
        System.out.println("║ 4️⃣  Water ..................... GHS 2       ║");
        System.out.println("╚═════════════════════════════════════════════╝");
        System.out.print("Enter choice:");
        switch (scanner.nextLine()) {
            case "1":
                return new Drink("Sobolo", 8);
            case "2":
                return new Drink("Malt", 10);
            case "3":
                return new Drink("Coke", 6);
            case "4":
                return new Drink("Water", 2);
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
