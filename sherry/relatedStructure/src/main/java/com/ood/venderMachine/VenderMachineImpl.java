package com.ood.venderMachine;

import java.util.ArrayList;
import java.util.List;

public class VenderMachineImpl implements VenderMachine {


    private Inventory<Item> itemInventory;
    private Inventory<Coin> coinInventory;
    private double currentBalance;
    private Item selectItem = null;

    public VenderMachineImpl() {
        initialize();
    }

    private void initialize() {
        for (Item i : Item.values()) {
            itemInventory.add(i, 5);
        }
        for (Coin i : Coin.values()) {
            coinInventory.add(i, 5);
        }
        currentBalance = 0;
    }

    @Override
    public double selectItemAndGetPrice(Item item) {
        if (itemInventory.getInventory(item) == 0) {
            throw new NoEnoughInventoryException();
        }
        this.selectItem = item;
        return item.getPrice();
    }

    @Override
    public void insertCoin(Coin coin) {
        currentBalance = currentBalance + coin.getValue();
        coinInventory.add(coin);
    }

    @Override
    public void reset() {
        coinInventory = null;
        itemInventory = null;
        currentBalance = 0.0;

    }

    @Override
    public Bucket collectItemAndChange() {
        try {
            if (currentBalance >= selectItem.getPrice()) {
                List<Coin> changes = getChanges(currentBalance - selectItem.getPrice());
                return new Bucket(this.selectItem, changes);
            }else{
                throw  new InSufficientFundsExcaption();
            }
        }catch (NoEnoughInventoryException e) {
            List<Coin> refunds = refund();
            return new Bucket(null, refunds);
        }
    }

    private List<Coin> getChanges(double changes) {
        List<Coin> changesList = new ArrayList<>();
        while (changes >= Coin.QUATER_CENT.getValue() && coinInventory.getInventory(Coin.QUATER_CENT) > 0) {
            changes -= Coin.QUATER_CENT.getValue();
            changesList.add(Coin.QUATER_CENT);
            coinInventory.removeOneFromInventory(Coin.QUATER_CENT);
        }
        while (changes >= Coin.TEN_CENT.getValue() && coinInventory.getInventory(Coin.TEN_CENT) > 0) {
            changes -= Coin.TEN_CENT.getValue();
            changesList.add(Coin.TEN_CENT);
            coinInventory.removeOneFromInventory(Coin.TEN_CENT);
        }
        while (changes >= Coin.FIVE_CENT.getValue() && coinInventory.getInventory(Coin.FIVE_CENT) > 0) {
            changes -= Coin.FIVE_CENT.getValue();
            changesList.add(Coin.FIVE_CENT);
            coinInventory.removeOneFromInventory(Coin.FIVE_CENT);
        }
        while (changes >= Coin.ONE_CENT.getValue() && coinInventory.getInventory(Coin.ONE_CENT) > 0) {
            changes -= Coin.ONE_CENT.getValue();
            changesList.add(Coin.ONE_CENT);
            coinInventory.removeOneFromInventory(Coin.ONE_CENT);
        }
        if (changes > 0) {
            //getChanges failed and refund
            for (Coin c : changesList) {
                coinInventory.add(c);
            }
            throw new NoEnoughInventoryException();
        }
        return changesList;

    }

    @Override
    public List<Coin> refund() {
        List<Coin> coins = getChanges(currentBalance);
        this.selectItem = null;
        return coins;

    }


}
