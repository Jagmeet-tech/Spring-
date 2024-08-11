package org.example.spring_boot_core;

import org.example.spring_boot_core.comp.Father;
import org.example.spring_boot_core.comp.Student;
import org.example.spring_boot_core.controllers.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import sample.Car;
import sample.Engine;

@SpringBootApplication
@ComponentScan(basePackages = {"sample","org.example.spring_boot_core"})
public class SpringBootCoreApplication {

	public static void main(String[] args) throws InterruptedException {

		// Bootstraps our application
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootCoreApplication.class, args);
//		Student student = applicationContext.getBean(Student.class);
//		Car car = applicationContext.getBean(Car.class);
//		Engine engine = applicationContext.getBean(Engine.class);
//		student.study();
//		car.run();
//		engine.work();

		Father father = applicationContext.getBean("father",Father.class);
		father.useStudent();

//		HomeController homeController = applicationContext.getBean(HomeController.class);
//		homeController.loginUser();
//		homeController.logutUser();

//		Car c1 = applicationContext.getBean(Car.class);
//		Car c2 = applicationContext.getBean(Car.class);
//		System.out.println(c1);
//		System.out.println(c2);
	}

}
