package com.example.anil.hackathon;



public class canteen {

    canteen() {}

    private String name;
    private String price;


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


    public canteen(String name, String price) {
        this.name = name;
        this.price = price;

    }

    @Override
    public String toString() {
        return
                "Dish Name: " + name + "\n" +
                " Price: " + price ;

    }
}
