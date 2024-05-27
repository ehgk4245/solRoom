package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.ImageOfBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageOfBoardRepository extends JpaRepository<ImageOfBoard,Long> {
    void deleteByBoardTypeAndBoardId(BoardType boardType, Long boardId);

//    @Query("SELECT i FROM ImageOfBoard i " +
//            "LEFT JOIN FreeBoard f ON i.boardId = f.id AND i.boardType = 'FREE' " +
//            "LEFT JOIN BarterBoard b ON i.boardId = b.id AND i.boardType = 'BARTER' " +
//            "LEFT JOIN RecipeBoard r ON i.boardId = r.id AND i.boardType = 'RECIPE' " +
//            "LEFT JOIN InteriorBoard inb ON i.boardId = inb.id AND i.boardType = 'INTERIOR' " +
//            "WHERE i.id = :imageId")
//    ImageOfBoard findByImageId(@Param("imageId") Long imageId);


}
