package com.example.nguyenthingocgiau_2123110205.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private String productName;
    private int quantity;
    private int oldPrice;
    private int newPrice;
    private int imageResource;

    public OrderItem(String productName, int quantity, int oldPrice, int newPrice, int imageResource) {
        this.productName = productName;
        this.quantity = quantity;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.imageResource = imageResource;
    }

    // Getters
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public int getOldPrice() { return oldPrice; }
    public int getNewPrice() { return newPrice; }
    public int getImageResource() { return imageResource; }
    // ✅ Thêm hàm này để tránh lỗi
    public int getImageRes() {
        return imageResource;
    }
    public String getName() {
        return productName;
    }

}
