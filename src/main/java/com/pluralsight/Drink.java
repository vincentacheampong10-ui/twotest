package com.pluralsight;
import java.util.Scanner;

public class Drink {
    private String name;
    private double price;

    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static Drink createFromUserInput(Scanner scanner) {
        System.out.println("Choose a drink (1.Sobolo 8, 2.Malt 10, 3.Coke 6, 4.Water 2): ");
        switch (scanner.nextLine()) {
            case "1":
                return new Drink("Sobolo", 8);
            case "2":
                return new Drink("Malt", 10);
            case "3":
                return new Drink("Coke", 6);
            case "4":
                return new Drink("Water", 2);
            default: ///Had trouble fixing default
                System.out.println("Invalid selection. Select the above options");
                return null;
        }
    }

    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " (GHS " + price + ")";
    }
}
