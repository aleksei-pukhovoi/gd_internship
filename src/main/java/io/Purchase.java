package io;

import java.io.Serializable;

public class Purchase implements Serializable{

    private long amount;
    private Category category;
    private double itemPrice;

    public Purchase() {
    }

    public Purchase(long amount, Category category, double itemPrice) {
        this.amount = amount;
        this.category = category;
        this.itemPrice = itemPrice;
    }

    public long getAmount() {
        return amount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public Category getCategory() {
        return category;
    }
}
