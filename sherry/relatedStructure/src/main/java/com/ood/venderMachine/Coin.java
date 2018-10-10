package com.ood.venderMachine;

public enum Coin {

    ONE_CENT(0.01), FIVE_CENT(0.5), TEN_CENT(0.1), QUATER_CENT(0.25);
    
    double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
