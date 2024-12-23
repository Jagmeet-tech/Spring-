package com.example.spring_jpa_ecom.listeners;

import com.example.spring_jpa_ecom.entities.Category;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

public class CategoryEventListener {

    @PrePersist
    public void beforeSave(Category cat){
        System.out.println("before persist..........->" + cat.getTitle());
    }

    @PostPersist
    public void postSave(Category cat){
        System.out.println("After persist...........->" + cat.getTitle());
    }
}
