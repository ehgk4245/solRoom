package com.solRoom.solspring.controller;

import com.solRoom.solspring.controller.dto.MemberDTO;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.service.MemberService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String processRegistrationForm(@Valid @ModelAttribute("member")MemberDTO.RequestMemberDTO memberDTO,
                                          BindingResult bindingResult,
                                          Model model) {
        // 유효성 검사 오류 확인
        if (bindingResult.hasErrors()) {
            model.addAttribute("member",memberDTO);
            return "joinForm";
        }

        // 이메일 중복 검사
        if (memberService.isDuplicateEmail(memberDTO)) {
            // 중복된 이메일이 있는 경우 회원가입 폼으로 리다이렉트하고 에러 메시지를 전달
           model.addAttribute("errorMessage","이미 가입된 이메일 주소입니다.");
            return "joinForm";
        }
        System.out.println("ㅇㅇㅇㅇㅇ");
        // 유효성 검사와 이메일 중복 검사를 모두 통과한 경우 회원가입 처리
        memberService.join(memberDTO);

        System.out.println("완료!");
        // 회원가입 성공 후 로그인 폼으로 리다이렉트
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }

}
