package com.example.pos;

public class Delete_CartModel {
    int image;
    String name;
    String price;
    String rate;

    public Delete_CartModel(int image, String name, String price, String rate) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
