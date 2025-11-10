package com.pluralsight;

import java.util.ArrayList;
import java.util.List;


public class JollofMeal extends Meal {
    private double basePrice; // Local copy for calculatePrice()
    private String size;
    private double sizeMultiplier;
    private String protein;
    private double proteinCost;
    private List<AddOn> addOns = new ArrayList<>();

    public JollofMeal(String name, double basePrice) {
        super(name, basePrice);
        this.basePrice = basePrice;
    }

    @Override
    public double calculatePrice() {
        double addOnTotal = 0;
        for (AddOn addOn : addOns) addOnTotal += addOn.getCost();
        // riceCost field is not used in the UI, so it's assumed 0.0 or ignored
        return (basePrice + 0.0 + proteinCost + addOnTotal) * sizeMultiplier;
    }

    public void setSize(String size, double multiplier) {
        this.size = size;
        this.sizeMultiplier = multiplier;
    }

    public void setProtein(String protein, double cost) {
        this.protein = protein;
        this.proteinCost = cost;
    }

    public void addAddOn(AddOn addOn) {
        if (addOn != null) {
            this.addOns.add(addOn);
        }
    }

    // NEW: Accepts choices from UI, performs logic, returns meal (No Prompts/Scanner use)
    public static JollofMeal createFromChoices(String typeChoiceStr, String sizeChoice, String proteinChoice) {

        String mealType = "";
        double mealBasePrice = 0.0;
        int typeChoice;

        // 1. Process Base Type
        try {
            typeChoice = Integer.parseInt(typeChoiceStr);
        } catch (NumberFormatException e) {
            return null;
        }

        switch (typeChoice) {
            case 1:
                mealType = "Classic Jollof";
                mealBasePrice = 25;
                break;
            case 2:
                mealType = "Coconut Jollof";
                mealBasePrice = 30;
                break;
            case 3:
                mealType = "Party Jollof";
                mealBasePrice = 35;
                break;
            case 4:
                mealType = "Vegetarian Jollof";
                mealBasePrice = 27;
                break;
            default:
                return null;
        }

        JollofMeal jollof = new JollofMeal(mealType, mealBasePrice);

        // 2. Process Size
        if (sizeChoice.equals("2")) {
            jollof.setSize("Large", 1.5);
        } else {
            jollof.setSize("Regular", 1.0);
        }

        // 3. Process Protein
        switch (proteinChoice) {
            case "1":
                jollof.setProtein("Chicken", 8.0);
                break;
            case "2":
                jollof.setProtein("Beef", 10.0);
                break;
            case "3":
                jollof.setProtein("Fish", 12.0);
                break;
            case "4":
                jollof.setProtein("None", 0.0);
                break;
            default:
                jollof.setProtein("None", 0.0);
                break;
        }

        return jollof;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name)
                .append(" (GHS ")
                .append(String.format("%.2f", this.basePrice))
                .append(") - Size: ").append(this.size);

        sb.append(", Protein: ").append(this.protein);

        if (!addOns.isEmpty()) {
            sb.append(", Add-ons: ");
            for (int i = 0; i < addOns.size(); i++) {
                sb.append(addOns.get(i).getName());
                if (i < addOns.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(" [Total Meal Cost: GHS ")
                .append(String.format("%.2f", this.calculatePrice()))
                .append("]");

        return sb.toString();
    }
}