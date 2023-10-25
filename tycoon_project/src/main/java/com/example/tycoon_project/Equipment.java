package com.example.tycoon_project;

public class Equipment {

    private String name;
    private int cost;
    private int quantity;

    public Equipment(String n, int c, int q) {
        name = n;
        cost = c;
        quantity = q;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
