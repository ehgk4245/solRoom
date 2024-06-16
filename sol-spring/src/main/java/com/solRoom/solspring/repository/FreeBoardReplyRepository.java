package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.freeBoard.FreeBoard;
import com.solRoom.solspring.domain.freeBoard.FreeBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardReplyRepository extends JpaRepository<FreeBoardReply,Long> {
    List<FreeBoardReply> findByBoard(FreeBoard board);
}
