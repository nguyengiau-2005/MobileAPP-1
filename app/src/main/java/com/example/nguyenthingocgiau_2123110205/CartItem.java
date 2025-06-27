package com.example.nguyenthingocgiau_2123110205;

public class CartItem {
    private String name;
    private double price;
    private int imageResId;
    private int quantity;
    private boolean selected; // ✅ Thêm biến selected

    public CartItem(String name, double price, int imageResId, int quantity) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = quantity;
        this.selected = false; // Mặc định chưa được chọn
    }

    // ✅ Getter và setter cho selected
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // Getter & Setter khác
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
