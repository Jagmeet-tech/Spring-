package org.example.spring_boot_core.services;


public interface LoginService {

    void login() throws InterruptedException;

    void logout();
}
