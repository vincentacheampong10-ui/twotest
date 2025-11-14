package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class Combo extends Meal {



    private List<Object> list = new ArrayList<>();

    public Combo(String name, double basePrice) {
        super(name, basePrice);
    }

    public void addComponent(Object component) {
        if (component != null) {
            this.list.add(component);
        }
    }

    public List<Object> getComponents() {
        return this.list;
    }

    public void removeComponent ( Meal item) {
        if (item != null) {
            this.list.remove(item);
        }
    }


    @Override
    public double calculatePrice() {
        double total = this.basePrice; // Start with the fixed base price of the combo

        for (Object component : list) {
            if (component instanceof Meal) {
                total += ((Meal) component).calculatePrice();
            } else if (component instanceof Drink) {
                total += ((Drink) component).getPrice();
            } else if (component instanceof Dessert) {
                total += ((Dessert) component).getPrice();
            }
        }

        return total;
    }

    @Override
    public String toString() {
        //  need to update toString to list the components
        return name + " Combo [Price: GHS " + String.format("%.2f", calculatePrice()) + ", Components: " + list.size() + " added]";
    }
}
