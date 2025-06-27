// com.example.nguyenthingocgiau_2123110205.model.Cake
package com.example.nguyenthingocgiau_2123110205;
public class Cake {
    private String name;
    private String flavor;
    private double price;
    private int imageResId;

    public Cake(String name, String flavor, double price, int imageResId) {
        this.name = name;
        this.flavor = flavor;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getFlavor() { return flavor; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }
}
