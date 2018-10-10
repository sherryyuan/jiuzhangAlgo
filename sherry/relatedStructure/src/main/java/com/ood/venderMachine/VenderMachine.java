package com.ood.venderMachine;

import java.util.List;

public interface VenderMachine {

    double selectItemAndGetPrice(Item item);
    void insertCoin(Coin coin);
    void reset();
    Bucket collectItemAndChange();
    List<Coin> refund();

}
