package com.example.spring_jpa_ecom.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "jpa_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

//    @OneToOne(mappedBy = "category")
//    private Product product;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)  // parent entity
    private List<Product> productList;
}
