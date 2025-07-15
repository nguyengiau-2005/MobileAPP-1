package com.example.nguyenthingocgiau_2123110205.model;

public class Cake {
    private String name;
    private String flavor;
    private String category;
    private double price;
    private int imageResId;

    public Cake(String name, String flavor, String category, double price, int imageResId) {
        this.name = name;
        this.flavor = flavor;
        this.category = category;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
