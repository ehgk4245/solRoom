package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.FreeBoardDTO;
import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service
public class FreeBoardService implements BoardService {
    @Autowired
    private final BoardRepository boardRepository;
    @Transactional
    public void savePost(FreeBoardDTO boardDTO, Member member){
        FreeBoard board = boardDTO.toEntity(member);
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
        board.setViewCount(boardDTO.getViewCount());
        board.setCreateDate(boardDTO.getCreateDate());
        // 필요한 다른 필드 업데이트...

        boardRepository.save(board);
    }

    public void upLikeCount(Long boardId){
        FreeBoard board = boardRepository.findById(boardId)
                .orElseThrow(()->{
                   return new IllegalArgumentException("게시물을 찾을 수 없습니다.");
                });
        board.setLikeCount(board.getLikeCount()+1);
        boardRepository.save(board);
    }

    public void downLikeCount(Long boardId) {
        FreeBoard board = boardRepository.findById(boardId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("게시물을 찾을 수 없습니다.");
                });
        board.setLikeCount(board.getLikeCount()-1);
        boardRepository.save(board);
    }
    public void upViewCount(Long boardId){
        FreeBoard board = boardRepository.findById(boardId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("게시물을 찾을 수 없습니다.");
                });
        board.setViewCount(board.getViewCount()+1);
        boardRepository.save(board);
    }


}
