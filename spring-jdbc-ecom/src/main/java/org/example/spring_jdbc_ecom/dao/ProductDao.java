package org.example.spring_jdbc_ecom.dao;

import org.example.spring_jdbc_ecom.model.Product;
import org.example.spring_jdbc_ecom.model.ProductWithCategory;

import java.util.List;


public interface ProductDao {

    Product create(Product product);

    Product update(Product product,int productId);

    void delete(int productId);

    List<Product> getAll();

    Product get(int productId);

    List<Product> search(String keyword);

    List<Product> getAllByCategory(int catId);

    List<ProductWithCategory> getAllWithCategory();
}
