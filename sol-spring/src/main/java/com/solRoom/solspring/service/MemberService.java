package com.solRoom.solspring.service;

import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member registerMember(Member member){
        return memberRepository.save(member);
    }

    public boolean isEmailExists(String email){
        return memberRepository.findByEmail(email) != null;
    }
}
