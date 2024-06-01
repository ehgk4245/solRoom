package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.BarterBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarterBoardRepository extends JpaRepository<BarterBoard,Long> {

    Page<BarterBoard> findByCategoryId(Long categoryId, Pageable pageable);

    Page<BarterBoard>findByProductNameContainingOrContentContaining(String productName, String content,Pageable pageable);

}
