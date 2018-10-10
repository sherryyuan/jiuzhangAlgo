package com.ood.venderMachine;

public enum Item {

    COKE(0.5), SEVEN_UP(0.2);

    double price;

    Item(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
