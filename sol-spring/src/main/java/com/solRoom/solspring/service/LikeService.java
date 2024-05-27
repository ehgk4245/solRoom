package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.MemberDTO;
import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.Like;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {
    @Autowired
    private final LikeRepository likeRepository;

    public void likePost(Member member, Long boardId, BoardType boardType) {
        Optional<Like> like = likeRepository.findByMemberAndBoardIdAndBoardType(member, boardId, boardType);
        if(like.isEmpty()){
            likeRepository.save(new Like(null,member,boardId,boardType));
        }
    }
    public void unlikePost(Member member, Long boardId, BoardType boardType) {
        Optional<Like> like = likeRepository.findByMemberAndBoardIdAndBoardType(member, boardId, boardType);
        if(like.isPresent()){
            likeRepository.delete(like.get());
        }
    }

    public boolean isLikedByMember(Member member, Long boardId, BoardType boardType){
        return likeRepository.findByMemberAndBoardIdAndBoardType(member,boardId,boardType).isPresent();
    }



}
