package com.solRoom.solspring.service;

import com.solRoom.solspring.domain.Board;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Transactional
    public void savePost(Board board, Member member){
        board.setCount(0);
        board.setMember(member);
        boardRepository.save(board);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Board viewDetail(Long Id){
        return boardRepository.findById(Id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
