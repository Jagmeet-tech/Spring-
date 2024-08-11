package org.example.spring_boot_core.services;

import org.springframework.stereotype.Component;

@Component
public class MySqlLoginService implements LoginService{
    @Override
    public void login() throws InterruptedException {
        System.out.println("Loggin user using mysql service");
        System.out.println("wait.....");
        Thread.sleep(5000);
        System.out.println("working..");
        Thread.sleep(5000);
    }

    @Override
    public void logout() {
        System.out.println("Logging out user using mysql service");
    }
}
