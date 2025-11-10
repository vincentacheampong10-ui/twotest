package com.pluralsight;

import java.util.Scanner;

public class Dessert {
    private String name;
    private double price;

    public Dessert(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static Dessert createFromUserInput(Scanner scanner) {
        System.out.println("Choose dessert (1.Cake 8, 2.Chin Chin 6, 3.Fruit Cup 7, 4.None 0): ");
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

    @Override
    public String toString() {
        return name + " (GHS " + price + ")";
    }
}
