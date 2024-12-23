package com.example.spring_jpa_ecom.repositories;

import com.example.spring_jpa_ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    //CRUD

    //1.Custom methods following => PROTOCOL

    List<Product> findByPrice(double price);
    List<Product> findByTitleAndPrice(String title , double price);
    int countByPrice(double price);
    boolean existsByTitle(String title);

    List<Product> findByTitleContaining(String keyword);
    List<Product> findByTitleNotContaining(String keyword);
    List<Product> findByTitleContainsIgnoreCase(String keyword);
    List<Product> findByTitleLike(String keywordWithWildCard);  // wildcard -> "%TV%"
    List<Product> findByTitleEndsWith(String suffixTitle);
    List<Product> findByTitleStartsWith(String prefixTitle);


    //2. Query methods (JPQL)
    @Query("select p from Product p")
    List<Product> getMyCustomQueryProducts();

    @Query("select p from Product p where p.title like %:keyword%")
    List<Product> searchProductsByTitle(@Param("keyword") String keyword);

    //3. Query methods (Native)
    @Query(nativeQuery = true,value = "select * from jpa_products")
    List<Product> getMyCustomNativeQueryProducts();


    // query parameters
    @Query("select p from Product p Where p.title =:title and p.price =:price")
    List<Product> getParamProductByTitleAndPrice(@Param("title") String title,@Param("price") double price);


    @Query("select p from Product p JOIN fetch p.category where p.category.title =:catTitle")
    List<Product> getProductByCategoryTitle(@Param("catTitle") String title);
}
