package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.BarterBoardDTO;
import com.solRoom.solspring.controller.dto.BoardImageUploadDTO;
import com.solRoom.solspring.domain.*;
import com.solRoom.solspring.domain.barterBoard.BarterBoard;
import com.solRoom.solspring.repository.BarterBoardRepository;
import com.solRoom.solspring.repository.ImageOfBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BarterBoardService implements BoardService<BarterBoard, BarterBoardDTO>{
    @Autowired
    private final BarterBoardRepository boardRepository;

    @Autowired
    private final ImageOfBoardRepository imageOfBoardRepository;


    @Value("${file.boardImagePath}")
    private String uploadFolder;

    @Override
    @Transactional
    public void savePost(BarterBoardDTO boardDTO, BoardImageUploadDTO boardImageUploadDTO, Member member) {

        BarterBoard board = boardDTO.toEntity(member);
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
                        .boardType(BoardType.BARTER)
                        .boardId(id)
                        .build();
                imageOfBoardRepository.save(image);
            }
        }
    }

    @Override
    public Page<BarterBoard> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public BarterBoardDTO viewDetail(Long id) {
        BarterBoard board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
        return BarterBoardDTO.fromEntity(board);
    }

    @Override
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
        imageOfBoardRepository.deleteByBoardTypeAndBoardId(BoardType.BARTER, id);
    }

    @Override
    public void updateBoard(Long id, BarterBoardDTO boardDTO) {
        BarterBoard board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Board Id: " + id)
        );
        // 기존 board 엔티티의 필드를 DTO의 값으로 업데이트
        board.setCategory(board.getCategory());
        board.setContent(board.getContent());
        board.setProductName(board.getProductName());
        board.setTradeType(board.getTradeType());
        board.setItemCondition(board.getItemCondition());
        board.setPriceRange(board.getPriceRange());
        board.setImageUrls(board.getImageUrls());
        board.setLocation(board.getLocation());

        boardRepository.save(board);
    }

    public List<BarterBoardDTO> findAll() {
        List<BarterBoard>boards = boardRepository.findAll();
        List<BarterBoardDTO>boardsDTO = new ArrayList<>();
        for(BarterBoard board : boards){
            boardsDTO.add(BarterBoardDTO.fromEntity(board));
        }
        return boardsDTO;

    }


    public Page<BarterBoardDTO> findByCategoryId(Long categoryId, Pageable pageable) {
        Page<BarterBoard> boardPage = boardRepository.findByCategory(categoryId, pageable);
        return boardPage.map(BarterBoardDTO::fromEntity);
    }

    public Page<BarterBoardDTO> findSearchList(String searchText1, String searchText2, Pageable pageable) {
        Page<BarterBoard> barterBoardPage = boardRepository.findByProductNameContainingOrContentContaining(searchText1, searchText2, pageable);
        return barterBoardPage.map(BarterBoardDTO::fromEntity);
    }
}
