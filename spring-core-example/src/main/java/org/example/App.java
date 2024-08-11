package org.example;

import org.example.components.Car;
import org.example.components.Engine;
import org.example.components.Wheel;
import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

//        Car car = new Car();          //first way
//        car.drive();

//          Wheel wheel = new Wheel();    //second way
//          Car car = new Car(wheel);
//          car.drive();

//          XML way Configuration
//        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
//        Wheel wheel = context.getBean(Wheel.class);
//        wheel.use();

//        Annotation way Configuration
//         ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//         Wheel wheel = context.getBean(Wheel.class);
//         Engine engine = context.getBean(Engine.class);
//         wheel.use();
//         engine.running();

          ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
          Car car = context.getBean(Car.class);
          car.drive();
    }
}
