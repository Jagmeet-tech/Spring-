package com.example.spring_jpa_ecom;

import com.example.spring_jpa_ecom.entities.Category;
import com.example.spring_jpa_ecom.entities.Product;
import com.example.spring_jpa_ecom.repositories.ProductRepository;
import in.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringJpaEcomApplicationTests {

	@Test
	void contextLoads() {
//		System.out.println("Working here");
	}

	@Autowired
	private ProductRepository productRepository;

	@Test
	void testProductRepository(){
		System.out.println("Testing product repository functions.");
//		List<Product> prod =  productRepository.findByTitleContaining("TV");
//		prod.forEach((item) -> System.out.println(item));

		List<Product> prod = productRepository.searchProductsByTitle("sung");
		prod.forEach(System.out::println);
	}

	@Autowired
	private CategoryRepository categoryRepository;
	@Test
	void createCategoryTest(){
		Category category = new Category();
		category.setId(2);
		category.setTitle("trending");

		categoryRepository.save(category);
	}


	@Test
	void setProductCatgeory(){
		 productRepository.findById(1).ifPresentOrElse(product -> {
			Category cat = categoryRepository.findById(1).get();
			 product.setCategory(cat);
			 productRepository.save(product);
		 },()->{
			 System.out.println("Product not found");
		 });
	}


	@Test
	void joinQueryTest(){
		//category title ke base pe fetch all products.
		List<Product> trending = productRepository.getProductByCategoryTitle("trending");
		trending.forEach(System.out::println);
	}

}
