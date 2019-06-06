package com.example.anil.hackathon;

/**
 * Created by Anil on 06/10/2018.
 */

public class Cart {
    Cart() {}

    private String name;
    private String price;
    private int quantity;
    private String status;
    private String confirmed;
    private  String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Name='" + name + '\n' +
                "Price='" + price + '\n'+" quantity = "+quantity
                + " Status: " + status + " " + confirmed + " Order made by: " + email;
    }

    public Cart(String name, String price, int quantity, String status, String confirmed, String email) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.confirmed = confirmed;
        this.email = email;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
