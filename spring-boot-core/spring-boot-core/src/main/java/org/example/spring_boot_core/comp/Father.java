package org.example.spring_boot_core.comp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Father {

    @Autowired
    @Qualifier("student4")
    private Student student;

//    public Father(@Qualifier("student1") Student student) {
//        this.student = student;
//        System.out.println("Creating Father");
//    }

    public void useStudent(){
        System.out.println("Father teaching student");
        student.study();
    }
}
