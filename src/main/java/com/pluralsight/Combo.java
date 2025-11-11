package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Combo extends Meal {

    private List<Object> components = new ArrayList<>();

    public Combo(String name, double basePrice) {
        super(name, basePrice);
    }

    public void addComponent(Object component) {
        if (component != null) {
            this.components.add(component);
        }
    }
    @Override
    public double calculatePrice() {
        double total = this.basePrice; // Start with the fixed base price of the combo

        // Iterate through all added components and sum their costs
        for (Object component : components) {
            if (component instanceof Meal) {
                total += ((Meal) component).calculatePrice();
            } else if (component instanceof Drink) {
                total += ((Drink) component).getPrice();
            } else if (component instanceof Dessert) {
                total += ((Dessert) component).getPrice();
            }
        }

        // You would apply discounts here if the logic required it.
        return total;
    }

    @Override
    public String toString() {
        // ... (You will also need to update toString to list the components) ...
        return name + " Combo [Price: GHS " + String.format("%.2f", calculatePrice()) + ", Components: " + components.size() + " added]";
    }
}
