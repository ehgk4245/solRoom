package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}

