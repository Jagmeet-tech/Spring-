package sample;

import org.springframework.stereotype.Component;

@Component
public class Engine {
    public void work(){
        System.out.println("Engine is exhausting");
    }
}
