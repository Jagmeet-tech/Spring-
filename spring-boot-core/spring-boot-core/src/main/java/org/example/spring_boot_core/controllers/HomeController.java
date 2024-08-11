package org.example.spring_boot_core.controllers;

import org.example.spring_boot_core.services.LoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HomeController {

    LoginService loginService;

//    public HomeController(@Qualifier("mySqlLoginService") LoginService loginService) {
//        this.loginService = loginService;
//    }

    public HomeController(LoginService loginService) {
        this.loginService = loginService;
    }

    public void loginUser() throws InterruptedException {
       loginService.login();
       System.out.println("Login Success");
    }

    public void logutUser(){
        loginService.logout();
    }
}
