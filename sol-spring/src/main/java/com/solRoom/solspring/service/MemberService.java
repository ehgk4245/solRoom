package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.MemberDTO;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.domain.RoleType;
import com.solRoom.solspring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public Member join(MemberDTO.RequestMemberDTO memberDTO) {
        memberDTO.encryptPassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));
        Member member = memberDTO.toEntity();
        member.setRole(RoleType.USER);
        return memberRepository.save(member);

    }

    public boolean isDuplicateEmail(MemberDTO.RequestMemberDTO memberDTO) {
        Optional<Member> findMember = memberRepository.findByEmail(memberDTO.getEmail());
        if(findMember.isPresent()){
            return true;
        }
        return false;
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String,String>validatorResult = new HashMap<>();
        for(FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }

    public boolean checkDuplicateEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
    public Member getCurrentMember() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + email));
    }

    public void profileUpdate(String profileImageUrl, String nickname, String statusMessage) {
        // 현재 사용자 정보를 가져옴
        Member currentMember = getCurrentMember();

        // 전달된 프로필 정보로 현재 사용자 정보 업데이트
        currentMember.setProfileImageUrl(profileImageUrl);
        currentMember.setNickname(nickname);
        currentMember.setStatusMessage(statusMessage);

        // 업데이트된 회원 정보 저장
        memberRepository.save(currentMember);
    }

    public void addressUpdate(String address){
        Member currentMember = getCurrentMember();

        currentMember.setAddress(address);

        memberRepository.save(currentMember);
    }
}
