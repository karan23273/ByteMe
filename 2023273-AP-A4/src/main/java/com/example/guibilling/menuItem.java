package com.example.guibilling;

public class menuItem {
    private String name;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String availability;
    private String category;
    public menuItem(String name, String price, String availability, String category) {
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.category = category;
    }
    public  menuItem(String [] list){
        this.name = list[0];
        this.price = list[1];
        this.availability = list[2];
        this.category = list[3];

    }
}
