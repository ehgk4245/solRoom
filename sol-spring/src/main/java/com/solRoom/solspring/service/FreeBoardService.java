package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.BoardImageUploadDTO;
import com.solRoom.solspring.controller.dto.FreeBoardDTO;
import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.ImageOfBoard;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.FreeBoardRepository;
import com.solRoom.solspring.repository.ImageOfBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class FreeBoardService implements BoardService<FreeBoard, FreeBoardDTO> {
    @Autowired
    private final FreeBoardRepository boardRepository;

    @Autowired
    private final ImageOfBoardRepository imageOfBoardRepository;

    @Value("${file.boardImagePath}")
    private String uploadFolder;

    @Transactional
    public void savePost(FreeBoardDTO boardDTO, BoardImageUploadDTO boardImageUploadDTO, Member member) {
        FreeBoard board = boardDTO.toEntity(member);
        boardRepository.save(board);
        Long id = board.getId();
        if (boardImageUploadDTO.getFiles() != null && !boardImageUploadDTO.getFiles().isEmpty()) {
            for (MultipartFile file : boardImageUploadDTO.getFiles()) {
                UUID uuid = UUID.randomUUID();
                String imageFileName = uuid + "_" + file.getOriginalFilename();
                File destinationFile = new File(uploadFolder + imageFileName);

                try {
                    file.transferTo(destinationFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String imageUrl = "/boardImages/" + imageFileName;
                board.getImageUrls().add(imageUrl);

                ImageOfBoard image = ImageOfBoard.builder()
                        .url(imageUrl)
                        .boardType(BoardType.FREE)
                        .boardId(id)
                        .build();
                imageOfBoardRepository.save(image);
            }
        }
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<FreeBoard> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public FreeBoardDTO viewDetail(Long Id) {
        FreeBoard board = boardRepository.findById(Id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
        return FreeBoardDTO.fromEntity(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
        imageOfBoardRepository.deleteByBoardTypeAndBoardId(BoardType.FREE, id);
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

    public void upLikeCount(Long boardId) {
        FreeBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("게시물을 찾을 수 없습니다.");
                });
        board.setLikeCount(board.getLikeCount() + 1);
        boardRepository.save(board);
    }

    public void downLikeCount(Long boardId) {
        FreeBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("게시물을 찾을 수 없습니다.");
                });
        board.setLikeCount(board.getLikeCount() - 1);
        boardRepository.save(board);
    }

    public void upViewCount(Long boardId) {
        FreeBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("게시물을 찾을 수 없습니다.");
                });
        board.setViewCount(board.getViewCount() + 1);
        boardRepository.save(board);
    }


}
