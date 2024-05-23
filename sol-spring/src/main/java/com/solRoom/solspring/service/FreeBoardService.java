package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.FreeBoardDTO;
import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.BoardRepository;
import com.solRoom.solspring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FreeBoardService implements BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Transactional
    public void savePost(FreeBoardDTO boardDTO, Member member){
        FreeBoard board = boardDTO.toEntity(member);
        board.setCount(0);
        boardRepository.save(board);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<FreeBoard> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public FreeBoard viewDetail(Long Id){
        return boardRepository.findById(Id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(Long id, FreeBoardDTO boardDTO) {
        FreeBoard board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Board Id: " + id)
        );


        // 기존 board 엔티티의 필드를 DTO의 값으로 업데이트
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setCount(boardDTO.getCount());
        board.setCreateDate(boardDTO.getCreateDate());
        // 필요한 다른 필드 업데이트...

        boardRepository.save(board);
    }

}
