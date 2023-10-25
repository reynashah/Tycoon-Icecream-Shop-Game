package com.example.tycoon_project;

public class RandomEvent {

    public RandomEvent(String n, double m) {
        name = n;
        money = m;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private double money;
}
