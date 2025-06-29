package com.example.nguyenthingocgiau_2123110205;

public class CartItem {
    private String name;
    private double price;
    private int imageResId;
    private int quantity;
    private boolean selected;

    public CartItem(String name, double price, int imageResId, int quantity) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = quantity;
        this.selected = true;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public int getQuantity() { return quantity; }
    public boolean isSelected() { return selected; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
