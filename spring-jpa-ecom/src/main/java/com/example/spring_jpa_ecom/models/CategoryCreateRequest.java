package com.example.spring_jpa_ecom.models;

public class CategoryCreateRequest {

    // define the members which are coming from incoming HTTP request.

    private String title;
    private String description;

    private boolean live;

    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return "CategoryCreateRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", live=" + live +
                ", price=" + price +
                '}';
    }

}
