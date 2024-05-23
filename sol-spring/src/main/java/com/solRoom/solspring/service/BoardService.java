package com.solRoom.solspring.service;

import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    //void savePost(<T>, Member member);  // 글작성

    Page<FreeBoard> boardList(Pageable pageable);   // 게시판 목록 보기

    FreeBoard viewDetail(Long id);  // 글 상세보기

    void deleteBoard(Long id);  // 글 삭제

}
