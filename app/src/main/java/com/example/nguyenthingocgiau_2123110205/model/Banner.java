package com.example.nguyenthingocgiau_2123110205.model;

public class Banner {
    private int imageResId;
    private String text;

    public Banner(int imageResId, String text) {
        this.imageResId = imageResId;
        this.text = text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }
}
