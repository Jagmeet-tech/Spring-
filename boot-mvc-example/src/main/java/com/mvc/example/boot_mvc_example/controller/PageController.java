package com.mvc.example.boot_mvc_example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@RestController = @Controller + @ResponseBody
//@Controller

@RestController
public class PageController {

    @RequestMapping("/about")
//    @ResponseBody
    public List<String> about(Model model){
//        System.out.println("this is about page.");
//        model.addAttribute("name","Spring MVC learning");
//        return "about";

        List<String> names = List.of("ankit","sharma","ravi");
        return names;
    }

    @GetMapping("/courses")
//    @ResponseBody
    public Map<String, Integer> getFees(){
        Map<String,Integer> map = new HashMap<>();
        map.put("Spring boot",10);
        map.put("Django",20);
        map.put("C++",30);

        return map;
    }
}
