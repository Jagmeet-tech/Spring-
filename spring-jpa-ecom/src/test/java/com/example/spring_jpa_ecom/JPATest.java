package com.example.spring_jpa_ecom;

import com.example.spring_jpa_ecom.entities.Category;
import com.example.spring_jpa_ecom.entities.Product;
import com.example.spring_jpa_ecom.services.CategoryService;
import com.example.spring_jpa_ecom.services.ProductService;
import in.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPATest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void criteriaTest(){
//        List<Category> categories = categoryService.getAllCategories();
//        categories.forEach(item -> System.out.println(item.getTitle()));


//        List<Product> product = categoryService.getProductBasedOnCategoryTitle();
//        product.forEach(item -> System.out.println(item.getTitle()));

        productService.transactCategoryWithProduct();
    }

    @Test
    public void entityEventTest(){
        Category cat = new Category();
        cat.setTitle("Furniture");
        categoryRepository.save(cat);
        System.out.println("Category is saved......");
    }
}
