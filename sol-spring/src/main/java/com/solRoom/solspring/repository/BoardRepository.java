package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.Board;
import com.solRoom.solspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BoardRepository extends JpaRepository<Board, Long> {

}

