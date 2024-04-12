package com.solRoom.solspring.controller;

import com.solRoom.solspring.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("member") Member member) {
        return "redirect:/registrationSuccess";
    }

    @GetMapping("/registrationSuccess")
    public String showRegistrationSuccessPage(){
        return "registrationSuccess";
    }
}
