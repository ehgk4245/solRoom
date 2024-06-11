package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.BarterBoardDTO;
import com.solRoom.solspring.controller.dto.BoardImageUploadDTO;
import com.solRoom.solspring.controller.dto.RecipeBoardDTO;
import com.solRoom.solspring.domain.*;
import com.solRoom.solspring.repository.ImageOfBoardRepository;
import com.solRoom.solspring.repository.RecipeBoardRepository;
import lombok.AllArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class RecipeBoardService implements BoardService<RecipeBoard, RecipeBoardDTO>{
    @Autowired
    private final RecipeBoardRepository boardRepository;

    @Autowired
    private final ImageOfBoardRepository imageOfBoardRepository;

    @Value("${file.boardImagePath}")
    private String uploadFolder;

    @Override
    public void savePost(RecipeBoardDTO boardDTO, BoardImageUploadDTO boardImageUploadDTO, Member member) {
        System.out.println("레시피");
        RecipeBoard board = boardDTO.toEntity(member);
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
                board.getDishImage().add(imageUrl);

                ImageOfBoard image = ImageOfBoard.builder()
                        .url(imageUrl)
                        .boardType(BoardType.RECIPE)
                        .boardId(id)
                        .build();
                imageOfBoardRepository.save(image);
            }
        }

    }

    @Override
    public Page<RecipeBoard> boardList(Pageable pageable) {
        return null;
    }

    @Override
    public RecipeBoardDTO viewDetail(Long id) {
        return null;
    }

    @Override
    public void deleteBoard(Long id) {

    }

    @Override
    public void updateBoard(Long id, RecipeBoardDTO boardDTO) {

    }

    public Page<RecipeBoardDTO> findSearchList(String searchText1, String searchText2, Pageable pageable) {
        Page<RecipeBoard> recipeBoardPage = boardRepository.findByTitleContainingOrIntroContaining(searchText1, searchText2, pageable);
        return recipeBoardPage.map(RecipeBoardDTO::fromEntity);
    }
}
