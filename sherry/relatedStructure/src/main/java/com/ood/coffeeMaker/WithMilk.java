package com.ood.coffeeMaker;

public class WithMilk extends CoffeeDecorator{

    public WithMilk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 0.5;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + " Milk";
    }
}
