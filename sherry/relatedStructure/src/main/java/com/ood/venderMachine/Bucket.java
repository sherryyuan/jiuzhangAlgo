package com.ood.venderMachine;

import java.util.List;

public class Bucket {
    private Item item;
    private List<Coin> coins;

    public  Bucket(Item item, List coins) {
        this.item = item;
        this.coins = coins;
    }

}
