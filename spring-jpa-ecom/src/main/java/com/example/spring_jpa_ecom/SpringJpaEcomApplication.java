package com.example.spring_jpa_ecom;

import com.example.spring_jpa_ecom.entities.Product;
import com.example.spring_jpa_ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaEcomApplication implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaEcomApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// We used CommandLineRunner as we cannot use productService non-static method in static main function.
		System.out.println("Application started running....");
//		Product product1 = new Product();
//		product1.setTitle("Google TV123");
//		product1.setDescription("Very bad product..");
//		product1.setPrice(3100.00);
//		product1.setLive(true);

//		Product updatedProduct = productService.create(product1);
//		System.out.println(updatedProduct);
//		System.out.println("Product created successfully...");

		System.out.println("Showing all products -------");
		productService.allProducts().forEach(product -> System.out.println(product));

		System.out.println("Single product ---------");
		System.out.println(productService.byId(2));

//		System.out.println("Deleting product --------");
//		productService.delete(3);
	}
}
