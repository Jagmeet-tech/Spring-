package org.example.spring_boot_core.comp;

import org.springframework.stereotype.Component;

@Component("student45")
public class Student {

    private String name = "default";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {
        System.out.println("Creating Student " + this.name);
    }

    public void study(){
        System.out.println("Student is now studying "+ this.name);
    }
}
