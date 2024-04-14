package com.solRoom.solspring.controller;

import com.solRoom.solspring.controller.dto.MemberDTO;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.service.MemberService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MemberController {
    @Autowired
    private  final MemberService memberService;

    //-----------------회원가입----------------------

    @PostMapping("/checkDuplicateEmail")
    public ResponseEntity<Map<String, Boolean>> checkDuplicateEmail(@RequestParam("email") String email) {
        boolean isDuplicate = memberService.checkDuplicateEmail(email);
        if(isDuplicate){
            System.out.println("중복");
        }
        else{
            System.out.println("중복아님");
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("duplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/newMember")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new MemberDTO.RequestMemberDTO());
        return "joinForm";
    }

    @PostMapping("/newMember")
    public String processRegistrationForm(@Valid MemberDTO.RequestMemberDTO memberDTO,
                                          Errors errors,
                                          Model model) {
       if(errors.hasErrors()){
           model.addAttribute("member",memberDTO);
           Map<String,String>validatorResult = memberService.validateHandling(errors);
           for(String key : validatorResult.keySet()){
               model.addAttribute(key,validatorResult.get(key));
           }
           return "joinForm";
       }
       memberService.join(memberDTO);
       return "redirect:/loginForm";

    }
    //------------------------------------------------

    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email")String email,
                        @RequestParam("password")String password){
        return "redirect:/";
    }


    @GetMapping("/registrationSuccess")
    public String showRegistrationSuccessPage(){
        return "registrationSuccess";
    }
}
