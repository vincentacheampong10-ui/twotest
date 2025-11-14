package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class Combo extends Meal {

    private List<Object> components = new ArrayList<>();

    public Combo(String name, double basePrice) {
        super(name, basePrice);
    }

    // Add any component (Meal, Drink, Dessert, AddOn, etc.)
    public void addComponent(Object component) {
        if (component != null) {
            this.components.add(component);
        }
    }

    public List<Object> getComponents() {
        return this.components;
    }

    // Remove only Meals (used when customizing)
    public void removeComponent(Meal item) {
        if (item != null) {
            this.components.remove(item);
        }
    }

    @Override
    public double calculatePrice() {
        double total = this.basePrice;

        for (Object component : components) {
            if (component instanceof Meal) {
                total += ((Meal) component).calculatePrice();
            } else if (component instanceof Drink) {
                total += ((Drink) component).getPrice();
            } else if (component instanceof Dessert) {
                total += ((Dessert) component).getPrice();
            } else if (component instanceof AddOn) {
                total += ((AddOn) component).getCost();
            }
        }

        return total;
    }

    // A compact, single line description joining component names with " + "
    public String getCompactDescription() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            Object c = components.get(i);
            String namePart = "";

            if (c instanceof Meal) {
                namePart = ((Meal) c).getName();
            } else if (c instanceof Drink) {
                namePart = ((Drink) c).getName();
            } else if (c instanceof Dessert) {
                namePart = ((Dessert) c).getName();
            } else if (c instanceof AddOn) {
                namePart = ((AddOn) c).getName();
            } else {
                namePart = c.toString();
            }

            // Append without duplicate base combo name
            if (i > 0) sb.append(" + ");
            sb.append(namePart);
        }

        sb.append(".");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n--- ").append(name).append(" Combo ---\n");

        for (Object component : components) {
            if (component instanceof Meal) {
                sb.append("Meal: ").append(component.toString()).append("\n");
            } else if (component instanceof Drink) {
                sb.append("Drink: ").append(((Drink) component).getName())
                        .append(" (GHS ").append(((Drink) component).getPrice()).append(")\n");
            } else if (component instanceof Dessert) {
                sb.append("Dessert: ").append(((Dessert) component).getName())
                        .append(" (GHS ").append(((Dessert) component).getPrice()).append(")\n");
            } else if (component instanceof AddOn) {
                sb.append("Add-on: ").append(((AddOn) component).getName())
                        .append(" (GHS ").append(((AddOn) component).getCost()).append(")\n");
            } else {
                sb.append(component.toString()).append("\n");
            }
        }

        sb.append("Total Combo Price: GHS ")
                .append(String.format("%.2f", calculatePrice()))
                .append("\n");

        return sb.toString();
    }
}
