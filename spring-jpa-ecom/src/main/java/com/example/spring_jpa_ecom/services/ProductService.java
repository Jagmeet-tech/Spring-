package com.example.spring_jpa_ecom.services;

import com.example.spring_jpa_ecom.entities.Product;
import com.example.spring_jpa_ecom.repositories.ProductRepository;
import in.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    // Constructor Injection
    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product create(Product product){
       Product prod = productRepository.save(product);
       return prod;
    }

    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    public Product byId(int pid){
//        Product prod = productRepository.findById(pid).get();
        Product prod = productRepository.findById(pid).orElseThrow(()-> new RuntimeException("Product not found with productID:-" + pid));
        return prod;
    }

    public void delete(int pid){
        Product prod = this.byId(pid);
        productRepository.delete(prod);
        return;
    }

    public List<Product> getProductByPrice(double price){
        List<Product> byPrice = productRepository.findByPrice(price);
        return byPrice;
    }
}
