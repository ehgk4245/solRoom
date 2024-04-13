package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface jpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    Member findByEmail(String email);
}

