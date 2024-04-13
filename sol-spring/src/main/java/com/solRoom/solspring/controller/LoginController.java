package com.solRoom.solspring.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
    @GetMapping("/login.html")
    public String showLoginPage() {
        return "login"; // login.html 파일로 연결
    }

    @GetMapping("/login")
    public String redirectToLoginPage() {
        return "login"; // login.html 파일로 연결
    }
}
