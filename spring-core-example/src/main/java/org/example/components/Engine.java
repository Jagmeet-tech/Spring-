package org.example.components;

import org.springframework.stereotype.Component;

@Component // This single annotation will treat a class as a bean
public class Engine {

    public void running(){
        System.out.println("Engine has been exhausting");
    }
}
