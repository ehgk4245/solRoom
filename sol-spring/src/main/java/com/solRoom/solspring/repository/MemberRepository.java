package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
    //<Optional>Member findByEmail(String email);
    boolean existsByEmail(String email);
    Member findByEmail(String email);

}

