package com.example.nguyenthingocgiau_2123110205;

public class CartItem {
    private String name;
    private double price;
    private int quantity;
    private int imageResId;
    private boolean selected = true;

    public CartItem(String name, double price, int quantity, int imageResId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getImageResId() { return imageResId; }
    public boolean isSelected() { return selected; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
