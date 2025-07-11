package com.example.nguyenthingocgiau_2123110205;

public class Category {
    String id;
    String title;
    int image;
    boolean selected;
    private int icon;

    // ✅ Constructor đầy đủ
    public Category(String id, String title, int image, int icon,boolean selected) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.icon = icon;
        this.selected = selected;
    }

    // ✅ Constructor rút gọn để gọi từ HomeActivity
    public Category(String title, int image) {
        this.id = "";
        this.title = title;
        this.image = image;
        this.selected = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public int getIcon() {
        return icon;
    }

    public int getImage() {
        return image;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
