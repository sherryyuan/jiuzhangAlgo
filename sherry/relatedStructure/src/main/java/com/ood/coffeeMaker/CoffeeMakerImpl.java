package com.ood.coffeeMaker;

public class CoffeeMakerImpl implements CoffeeMaker {
    @Override
    public Coffee makeCoffee(CoffeePack coffeePack) {
        Coffee c = new SimpleCoffee();
        for(int i = 0; i < coffeePack.milk; i++) {
            c = new WithMilk(c);
        }
        for(int i = 0; i < coffeePack.suger; i++) {
            c = new WithSuger(c);
        }
        return c;
    }
}
