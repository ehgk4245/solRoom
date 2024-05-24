package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.Like;
import com.solRoom.solspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like>findByMemberAndBoardIdAndBoardType(Member member, Long boardId, Like.BoardType boardType);
}
