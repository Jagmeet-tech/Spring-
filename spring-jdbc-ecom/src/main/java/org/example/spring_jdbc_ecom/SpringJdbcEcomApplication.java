package org.example.spring_jdbc_ecom;

import org.example.spring_jdbc_ecom.dao.CategoryDao;
import org.example.spring_jdbc_ecom.dao.ProductDao;
import org.example.spring_jdbc_ecom.model.Category;
import org.example.spring_jdbc_ecom.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcEcomApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJdbcEcomApplication.class, args);

//		Product p1 = new Product();
//		p1.setId(2);
//		p1.setTitle("Iphone 15");
//		p1.setDescription("very nice product for advanced users");
//		p1.setPrice(12000);

		// IOC will give object of its implementation class ProductDaoImpl as we have annotated with @Repository.
		ProductDao productDao = context.getBean(ProductDao.class);
		CategoryDao categoryDao = context.getBean(CategoryDao.class);
//		productDao.create(p1);
//		System.out.println(p1);
//		List<Product> productList = productDao.getAll();
//		productList.forEach(product -> System.out.println(product));
//		System.out.println(productDao.get(2));


//		Category category1 = new Category();
//		category1.setId(102);
//		category1.setTitle("Mobile phones");
//		category1.setDescription("Best Collection of phones");
//		categoryDao.create(category1);
//
//		Category category2 = new Category();
//		category2.setId(103);
//		category2.setTitle("Iphones");
//		category2.setDescription("Best Collection of Iphones");
//		categoryDao.create(category2);
//
//		Product p1 = new Product();
//		p1.setId(2);
//		p1.setTitle("Android 10");
//		p1.setDescription("very nice product for advanced users");
//		p1.setPrice(12000);
//		p1.setCatId(category1.getId());
//		productDao.create(p1);
//
//		Product p2 = new Product();
//		p2.setId(3);
//		p2.setTitle("Iphone 10");
//		p2.setDescription("very good product for advanced users");
//		p2.setPrice(16000);
//		p2.setCatId(category2.getId());
//		productDao.create(p2);

		productDao.getAllWithCategory().forEach(pwc -> System.out.println(pwc));
	}

}
