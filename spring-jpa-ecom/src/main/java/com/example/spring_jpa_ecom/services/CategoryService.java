package com.example.spring_jpa_ecom.services;

import com.example.spring_jpa_ecom.entities.Category;
import com.example.spring_jpa_ecom.entities.Product;
import com.example.spring_jpa_ecom.exception.ResourceNotFoundException;
import in.repositories.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category getById(int categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
    }

    public Category create(Category category){
        return categoryRepository.save(category);
    }

    public CategoryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Category> getAllCategories(){
        // getting the criteria builder
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        //getting criteria query from builder , that will represent my actual query.
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);

        // Define the Root (representing the entity/table) source
        Root<Category> root = cq.from(Category.class);

        // Create a Predicate (for filtering)
        Predicate predicate1 = cb.equal(root.get("title"), "trending");
        Predicate predicate2 = cb.equal(root.get("id"), 2);

        Order titleOrder = cb.desc(root.get("title"));

        // add selection / filtering / clauses
        cq.select(root).where(predicate1,predicate2).orderBy(titleOrder);

        //build and execute query
        List<Category> resultList = entityManager.createQuery(cq).getResultList();
        return resultList;
    }

    public List<Product> getProductBasedOnCategoryTitle(){
        // Join example

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> productRoot = cq.from(Product.class);
        Join<Product,Category> categoryJoin = productRoot.join("category");

        Predicate predicate1 = cb.equal(categoryJoin.get("title"),"trending");

        cq.select(productRoot).where(predicate1);

        List<Product> res = entityManager.createQuery(cq).getResultList();
        return res;
    }
}
