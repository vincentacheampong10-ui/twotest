package com.pluralsight;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder = new Order();

    public void displayHome() {
        boolean running = true;
        while (running) {
            System.out.println("\n╔═══════════════════════════════════════════════╗");
            System.out.println("║          🍛  JOLLOF EXPRESS MENU              ║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║ 1.  New Order                                 ║");
            System.out.println("║ 0.  Exit                                      ║");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Enter choice:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    startNewOrder();
                    break;
                case "0":
                    System.out.println("Goodbye! 👋");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void startNewOrder() {
        currentOrder = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              JOLLOF EXPRESS MENU             ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  1. Add Jollof Meal                          ║");
            System.out.println("║  2. Add Drink                                ║");
            System.out.println("║  3. Add Side (Dessert)                       ║");
            System.out.println("║  4. Checkout                                 ║");
            System.out.println("║  5. Combo Deal (Save 20%)                    ║");
            System.out.println("║  0. Cancel Order                             ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Enter your choice:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    JollofMeal jollof = getJollofMealFromUser();
                    if (jollof != null) {
                        currentOrder.addMeal(jollof);
                        // Standardized Success Message for Jollof Meal
                        System.out.println("✅ " + jollof.getName() + " added to order.");
                    } else {
                        System.out.println("Jollof Meal creation failed.");
                    }
                    break;
                case "2":
                    Drink drink = Drink.createFromUserInput(scanner);
                    if (drink != null) {
                        currentOrder.setDrink(drink);
                        // Standardized Success Message for Drink
                        System.out.println("✅ " + drink.getName() + " added to order.");
                    } else {
                        System.out.println("Drink not added.");
                    }
                    break;
                case "3":
                    Dessert dessert = Dessert.createFromUserInput(scanner);
                    if (dessert != null) {
                        currentOrder.setDessert(dessert);
                        // Standardized Success Message for Dessert
                        System.out.println("✅ " + dessert.getName() + " added to order.");
                    } else {
                        System.out.println("Dessert not added.");
                    }
                    break;
                case "4":
                    if (currentOrder.calculateTotal() > 0) {
                        checkout();
                        ordering = false;
                    } else {
                        System.out.println("Order is empty! Please add an item before checking out.");
                    }
                    break;
                case "0":
                    System.out.println("Order canceled.");
                    ordering = false;
                    break;
                case "5":
                    selectCombo();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private JollofMeal getJollofMealFromUser() {
        // --- 1. Get Base Choices (Jollof Type, Size, Protein) ---
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                 1. CHOOSE JOLLOF TYPE            ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Classic Jollof        ($25)                  ║");
        System.out.println("║  2. Coconut Jollof        ($30)                  ║");
        System.out.println("║  3. Party Jollof          ($35)                  ║");
        System.out.println("║  4. Vegetarian Jollof     ($27)                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("Enter your choice:");
        String typeChoice = scanner.nextLine();

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                     2. CHOOSE SIZE              ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Regular (x1.0)                               ║");
        System.out.println("║  2. Large (x1.5)                                 ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("Enter your choice:");
        String sizeChoice = scanner.nextLine();

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                 3. CHOOSE PROTEIN               ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Chicken (+$8)                                ║");
        System.out.println("║  2. Beef (+$10)                                  ║");
        System.out.println("║  3. Fish (+$12)                                  ║");
        System.out.println("║  4. None (+$0)                                   ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("Enter your choice:");
        String proteinChoice = scanner.nextLine();

        // --- 2. Create Base Meal Object ---
        JollofMeal jollof = JollofMeal.createFromChoices(typeChoice, sizeChoice, proteinChoice);
        if (jollof == null) return null; // Exit if base meal creation failed


        boolean addingAddOns = true;
        int premiumCount = 0;
        final int premiumLimit = 3;

        while (addingAddOns) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║             4. ADD ADD-ONS                                 ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║  Premium Limit Remaining: " + (premiumLimit - premiumCount) + " left                       ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║  R) Regular Add-on      →  Fixed Cost                      ║");
            System.out.println("║  P) Premium Add-on      →  GHS 6 each                      ║");
            System.out.println("║  0) Done Adding Add-ons                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.print("Choose R, P, or 0:");
            String categoryChoice = scanner.nextLine().toUpperCase();

            switch (categoryChoice) {
                case "R":
                    handleRegularAddOns(jollof);
                    break;
                case "P":
                    premiumCount += handlePremiumAddOns(jollof, premiumCount, premiumLimit);
                    break;
                case "0":
                    addingAddOns = false;
                    break;
                default:
                    System.out.println("Invalid category choice. Try again.");
            }
        }
        return jollof;
    }

    private void handleRegularAddOns(JollofMeal jollof) {
        boolean choosingRegular = true;
        while (choosingRegular) {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║                 REGULAR ADD-ONS                  ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║  1) Plantain ............ (4.0)                  ║");
            System.out.println("║  2) Egg ................. (3.0)                  ║");
            System.out.println("║  3) Coleslaw ............ (2.0)                  ║");
            System.out.println("║  0) Back                                         ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("Enter choice:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    jollof.addAddOn(new AddOn("Plantain", 4.0));
                    break;
                case "2":
                    jollof.addAddOn(new AddOn("Egg", 3.0));
                    break;
                case "3":
                    jollof.addAddOn(new AddOn("Coleslaw", 2.0));
                    break;
                case "0":
                    choosingRegular = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private int handlePremiumAddOns(JollofMeal jollof, int currentCount, final int limit) {
        if (currentCount >= limit) {
            System.out.println("⚠️ Premium add-on limit reached (" + limit + ").");
            return 0;
        }

        boolean choosingPremium = true;
        int addedCount = 0;

        while (choosingPremium && currentCount + addedCount < limit) {
            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║               PREMIUM ADD-ONS (GHS 6 each)         ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.printf("║  Limit Remaining: %-35s║%n", (limit - (currentCount + addedCount)));
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║  1) Fried Plantain (Kelewele)                        ║");
            System.out.println("║  2) Extra Meat Portion                               ║");
            System.out.println("║  3) Fried Yam                                        ║");
            System.out.println("║  4) Avocado Slices                                   ║");
            System.out.println("║  0) Back                                             ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            System.out.print("Enter choice:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    jollof.addAddOn(new AddOn("Fried Plantain (Kelewele)", true));
                    addedCount++;
                    break;
                case "2":
                    jollof.addAddOn(new AddOn("Extra Meat Portion", true));
                    addedCount++;
                    break;
                case "3":
                    jollof.addAddOn(new AddOn("Fried Yam", true));
                    addedCount++;
                    break;
                case "4":
                    jollof.addAddOn(new AddOn("Avocado Slices", true));
                    addedCount++;
                    break;
                case "0":
                    choosingPremium = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
        if (currentCount + addedCount == limit) {
            System.out.println("✅ Premium limit reached.");
        }
        return addedCount;
    }

    // ... (rest of the class) ...

    private void checkout() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                   🧾 ORDER SUMMARY                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");

// Display current order details
        System.out.println(currentOrder);

        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.printf("║  💰 FINAL TOTAL: GHS %-30.2f ║%n", currentOrder.calculateTotal());
        System.out.println("╚══════════════════════════════════════════════════════╝");

        System.out.print("\n✅ Confirm order? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            ReceiptManager.saveReceipt(currentOrder);
        } else {
            System.out.println("Order canceled.");
        }
    }

    private void selectCombo() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                🍱 SPECIAL COMBO DEALS                ║");
        System.out.println("╠══════════╦══════════════════════════════╦════════════╣");
        System.out.println("║ Option   ║ Combo                        ║ Price (GHS) ║");
        System.out.println("╠══════════╬══════════════════════════════╬════════════╣");
        System.out.printf("║ %-8s ║ %-28s ║ %-10.2f ║%n", "1", "Jollof + Drink Combo (Save 10%)", 30.00);
        System.out.printf("║ %-8s ║ %-28s ║ %-10.2f ║%n", "2", "Party Pack (3 Meals + Sides)", 85.00);
        System.out.printf("║ %-8s ║ %-28s ║ %-10.2f ║%n", "3", "Student Meal Deal (Budget Size)", 20.00);
        System.out.printf("║ %-8s ║ %-28s ║ %-10.2f ║%n", "4", "None", 0.00);
        System.out.println("╚══════════╩══════════════════════════════╩════════════╝");
        System.out.print("\n👉 Enter combo choice: ");
        String comboChoice = scanner.nextLine();

        Combo combo = null;
        String name;
        double price;

        switch (comboChoice) {
            case "1":
                name = "Jollof + Drink Combo";
                price = 30.00;
                // Assuming Combo is now ComboItem or Combo with appropriate constructor
                combo = new Combo(name, price);
                break;
            case "2":
                name = "Party Pack";
                price = 85.00;
                combo = new Combo(name, price);
                break;
            case "3":
                name = "Student Meal Deal";
                price = 20.00;
                combo = new Combo(name, price);
                break;
            case "4":
                System.out.println("No combo selected.");
                return;
            default:
                System.out.println("Invalid combo choice.");
                return;
        }

        if (combo != null) {
            currentOrder.addMeal(combo);
            // Standardized Success Message for Combo
            System.out.println("✅ " + combo.getName() + " added to order.");
            customizeCombo(combo); // Continue to customization
        }
    }

    private void customizeCombo(Combo combo) {
        boolean customizing = true;
        while (customizing) {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.printf("║           ⚙️  CUSTOMIZE %-20s ║%n", combo.getName().toUpperCase());
            System.out.println("╠════════════════════════════════════════════════════╣");
            System.out.printf("║ Current Combo Price: GHS %-24.2f ║%n", combo.calculatePrice());
            System.out.println("╠════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Add another Jollof Meal (Custom)                ║");
            System.out.println("║ 2. Add another Drink                              ║");
            System.out.println("║ 3. Add another Side (Dessert)                     ║");
            System.out.println("║ 0. Done Customizing                               ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.print("Choose an option:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Use the existing Jollof customization tool
                    JollofMeal jollof = getJollofMealFromUser();
                    if (jollof != null) {
                        combo.addComponent(jollof); // Assumes Combo has an addComponent method
                        System.out.println("✅ " + jollof.getName() + " added to combo.");
                    }
                    break;
                case "2":
                    Drink drink = Drink.createFromUserInput(scanner);
                    if (drink != null) {
                        combo.addComponent(drink); // Assumes Combo can hold Drinks
                        System.out.println("✅ " + drink.getName() + " added to combo.");
                    }
                    break;
                case "3":
                    Dessert dessert = Dessert.createFromUserInput(scanner);
                    if (dessert != null) {
                        combo.addComponent(dessert); // Assumes Combo can hold Desserts
                        System.out.println("✅ " + dessert.getName() + " added to combo.");
                    }
                    break;
                case "0":
                    customizing = false;
                    System.out.println("Customization complete.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}