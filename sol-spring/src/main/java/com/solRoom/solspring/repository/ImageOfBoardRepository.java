package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.ImageOfBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageOfBoardRepository extends JpaRepository<ImageOfBoard,Long> {
    void deleteByBoardTypeAndBoardId(BoardType boardType, Long boardId);



}
