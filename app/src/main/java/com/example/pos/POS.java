package com.example.pos;

public class POS {
    String productname;
    String quantity;
    String price;
    String total;

    public POS(String productname, String quantity, String price, String total) {
        this.productname = productname;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
