package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.BoardImageUploadDTO;
import com.solRoom.solspring.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService<T,D> {
    void savePost(D boardDTO, BoardImageUploadDTO boardImageUploadDTO, Member member);  // 글작성

    Page<T> boardList(Pageable pageable);   // 게시판 목록 보기

    D viewDetail(Long id);  // 글 상세보기

    void deleteBoard(Long id); // 글 삭제

    void updateBoard(Long id, D boardDTO); // 글 수정

}
