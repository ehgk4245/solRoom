package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.barterBoard.BarterBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarterBoardRepository extends JpaRepository<BarterBoard,Long> {

    Page<BarterBoard> findByCategory(Long categoryId, Pageable pageable);

    Page<BarterBoard>findByProductNameContainingOrContentContaining(String productName, String content,Pageable pageable);

}
