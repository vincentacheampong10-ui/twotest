package com.pluralsight;

public abstract class Meal {
    protected String name;
    protected double basePrice;

    public Meal(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public abstract double calculatePrice();

    public String getName() {
        return name;
    }
}


