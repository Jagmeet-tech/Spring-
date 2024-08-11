package sample;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Car {

    public void run(){
        System.out.println("Car is running");
    }
}
