package com.ood.coffeeMaker;

public abstract class CoffeeDecorator implements  Coffee{
    protected final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    public double getPrice() {
        return decoratedCoffee.getPrice();
    }

    public String getIngredients() {
        return decoratedCoffee.getIngredients();
    }
}
