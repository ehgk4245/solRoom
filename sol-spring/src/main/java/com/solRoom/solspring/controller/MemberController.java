package com.solRoom.solspring.controller;

import com.solRoom.solspring.controller.dto.MemberDTO;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MemberController {
    private  final MemberService memberService;
    @GetMapping("/newMember")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "newMember";
    }

    @PostMapping("/newMember")
    public String processRegistrationForm(@ModelAttribute MemberDTO memberDTO) {
        memberService.join(memberDTO);
        System.out.println("저장완료!");
        return "redirect:/";
    }

    @GetMapping("/registrationSuccess")
    public String showRegistrationSuccessPage(){
        return "registrationSuccess";
    }
}
