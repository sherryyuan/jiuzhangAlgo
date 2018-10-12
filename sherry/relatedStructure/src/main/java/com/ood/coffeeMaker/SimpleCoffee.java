package com.ood.coffeeMaker;

public class SimpleCoffee implements  Coffee{

    @Override
    public double getPrice() {
        return 2.0;
    }

    public String getIngredients() {
        return "simple coffee ";
    }
}
