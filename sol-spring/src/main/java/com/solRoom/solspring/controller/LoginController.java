package solroom.solspring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
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
