package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Meal> meals = new ArrayList<>();
    private Drink drink;
    private Dessert dessert;

    public Order(List<Meal> meals, Drink drink, Dessert dessert) {
        this.meals = meals;
        this.drink = drink;
        this.dessert = dessert;
    }

    public Order() {

    }

    public void addMeal(Meal meal) {
        if (meal != null) {
            this.meals.add(meal);
        } else {
            System.out.println("Warning: Empty meal.");
        }
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    public double calculateTotal() {
        double total = 0;
        for (Meal meal : meals) {
            total += meal.calculatePrice();
        }
        // Adds drink price if present
        if (drink != null) {
            total += drink.getPrice();
        }

        // Adds dessert price if present
        if (dessert != null) {
            total += dessert.getPrice();
        }
        return total;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        // Append all meals
        for (Meal m : meals) {
            stringBuilder.append(m).append("\n");
        }

        // Append drink and dessert if they exist
        if (drink != null) {
            stringBuilder.append("Drink: ").append(drink).append("\n");
        }
        if (dessert != null) {
            stringBuilder.append("Dessert: ").append(dessert).append("\n");
        }

        return stringBuilder.toString();
    }
}

