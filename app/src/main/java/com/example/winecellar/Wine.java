package com.example.winecellar;

public class Wine {
    private int id;
    private String name;
    private String grape;
    private double price;
    private int amount;
    private int age;
    private String type;

    public Wine(int id, String name, String grape, double price, int amount, int age, String type) {
        this.id = id;
        this.name = name;
        this.grape = grape;
        this.price = price;
        this.amount = amount;
        this.age = age;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrape() {
        return grape;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }
}
