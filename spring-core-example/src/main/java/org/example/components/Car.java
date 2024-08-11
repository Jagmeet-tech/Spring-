package org.example.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

//    Wheel wheel = new Wheel();      // Tight coupling :- (manually change if different type of wheel will use)

    Wheel wheel;

//------------Field injection-----------
    @Autowired
    Engine engine;

    public Car(Wheel wheel) {
        this.wheel = wheel;
    }

//-------------Constructor injection------------------
//    public Car(Wheel wheel,Engine engine) {
//        this.wheel = wheel;
//        this.engine = engine;
//    }


//-------------Setter injection------------------
//    @Autowired
//    public void setWheel(Wheel wheel){
//        System.out.println("Setting wheel object");
//        this.wheel = wheel;
//    }

    public void drive(){
        System.out.println("Car is started");
        wheel.use();
        engine.running();
    }
}
