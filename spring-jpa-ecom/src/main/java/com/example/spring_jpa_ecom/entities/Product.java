package com.example.spring_jpa_ecom.entities;

import jakarta.persistence.*;

@Entity
@Table(name="jpa_products")
public class Product {

    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_title",unique = true,nullable = false,length = 500)
    private String title;

    private String description;
    private double price;
    private boolean isLive = false;

    public Product(int id, String title, String description, double price, boolean isLive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.isLive = isLive;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

//    @OneToOne
//    private Category category;

    @ManyToOne
    private Category category;  // owning side for managing relationships (store foreign key)   // child entity

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isLive=" + isLive +
                '}';
    }
}
