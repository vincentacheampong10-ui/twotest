package com.pluralsight.model;

import java.util.Scanner;

public class Signature {
    private JollofMeal signature() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                 1. CHOOSE JOLLOF TYPE            ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Classic Jollof        ($25)                  ║");
        System.out.println("║  2. Coconut Jollof        ($30)                  ║");
        System.out.println("║  3. Party Jollof          ($35)                  ║");
        System.out.println("║  4. Vegetarian Jollof     ($27)                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("Enter your choice:");
        String typeChoice = scanner.nextLine();

        Combo combo = null;
        String name;
        double price;

        switch (typeChoice) {
            case "1":
                name = "Classic Jollof";
                price = 30.00;
                combo = new Combo(name, price);
                break;
            case "2":
                name = "Veg Jollof";
                price = 85.00;
                combo = new Combo(name, price);
                break;
            case "3":
                name = "Party Jollof";
                price = 90.00;
                combo = new Combo(name, price);
                break;
            case"4":
                name="Vegetarian Jollof";
                price= 90.00;
            case "5":
                System.out.println("None.");
                return null;
            default:
                System.out.println("Invalid Signature meal choice.");
                return null;
        }

        // --- 2. Create Base Meal Object ---
        JollofMeal jollof = JollofMeal.createFromChoices(typeChoice, "", "");
        if (jollof == null) return null; // Exit if base meal creation failed
        return jollof;
    }
}

