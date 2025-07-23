package com.example.nguyenthingocgiau_2123110205.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String productName;
    private double price;
    private int quantity;
    private int imageResource;
    private boolean selected;

    private double oldPrice; // Thêm
    private double newPrice; // Thêm

    public CartItem(String productName, double price, int quantity, int imageResource) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.imageResource = imageResource;
        this.selected = false;

        this.oldPrice = price; // mặc định nếu chưa truyền oldPrice
        this.newPrice = price;
    }

    // Getters và Setters
    public String getProductName() {
        return productName;
    }

    public int getImageResId() {
        return imageResource;
    }

    public String getName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
