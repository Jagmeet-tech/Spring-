package com.example.spring_jpa_ecom.entities;

import com.example.spring_jpa_ecom.listeners.CategoryEventListener;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "jpa_category")
@EntityListeners(CategoryEventListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

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
//    @OneToOne(mappedBy = "category")
//    private Product product;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)  // parent entity
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

//   Not good practice to write here , instead use event listener classes for that.
//    @PrePersist
//    public void beforeSave(){
//        System.out.println("before persist..........");
//    }
//
//    @PostPersist
//    public void postSave(){
//        System.out.println("After persist...........");
//    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
