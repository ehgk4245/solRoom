package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.MemberDTO;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.MemberRepository;
import com.solRoom.solspring.repository.jpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    public boolean isEmailExists(String email){
        return memberRepository.findByEmail(email) != null;
    }

    public void join(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPassword(memberDTO.getPassword());
        member.setAddress(memberDTO.getAddress());
        memberRepository.save(member);
    }
}
