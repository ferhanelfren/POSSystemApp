package com.example.pos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.sql.Blob;

public class ArrayProductListssssss {
    String id;
    String imagename;
    byte[] image;
    String products;
    String brands;
    String categorys;
    String qtys;
    String prices;

    public ArrayProductListssssss() {
        this.id = id;
        this.imagename = imagename;
        this.image = image;
        this.products = products;
        this.brands = brands;
        this.categorys = categorys;
        this.qtys = qtys;
        this.prices = prices;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getCategorys() {
        return categorys;
    }

    public void setCategorys(String categorys) {
        this.categorys = categorys;
    }

    public String getQtys() {
        return qtys;
    }

    public void setQtys(String qtys) {
        this.qtys = qtys;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }
}
