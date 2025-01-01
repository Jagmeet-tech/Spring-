package com.elearn.app.start_learn_back.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping("/client-login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/success")
    public String successPage(){
        return "success";
    }
}
