package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    Member save(Member member);
    Member findByEmail(String email);
}
