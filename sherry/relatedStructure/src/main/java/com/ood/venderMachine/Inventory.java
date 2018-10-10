package com.ood.venderMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {

    Map<T, Integer> inventory = new HashMap<>();


    public void add(T t) {
        add(t, 1);
    }

    public void add(T t, Integer num) {
        if (inventory.containsKey(t)) {
            inventory.put(t, inventory.get(t) + num);
        } else {
            inventory.put(t, num);
        }

    }

    public int getInventory(T t) {
        return inventory.get(t);
    }

    public void cleanInventory() {
        this.inventory = new HashMap<>();
    }

    public void removeFromInventory(T t, Integer num) {
        if (inventory.get(t) >= num) {
            inventory.put(t, inventory.get(t) - num);
        } else {
            throw new NoEnoughInventoryException();
        }
    }

    public void removeOneFromInventory(T t) {
        removeFromInventory(t, 1);
    }

}
