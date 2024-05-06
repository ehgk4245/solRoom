package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
