package com.example.nguyenthingocgiau_2123110205.model;

public class Cake {
    private String name;
    private String flavor;
    private String category;
    private double price;
    private int imageResId;
    private String description; // 🔸 Thêm thuộc tính này

    public Cake(String name, String flavor, String category, double price, int imageResId, String description) {
        this.name = name;
        this.flavor = flavor;
        this.category = category;
        this.price = price;
        this.imageResId = imageResId;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
