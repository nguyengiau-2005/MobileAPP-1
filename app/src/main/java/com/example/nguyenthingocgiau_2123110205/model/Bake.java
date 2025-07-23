package com.example.nguyenthingocgiau_2123110205.model;

public class Bake {
    private int imageRes;
    private String title;
    private String description;

    public Bake(int imageRes, String title, String description) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
    }

    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
