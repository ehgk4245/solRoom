package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.BarterBoard;
import com.solRoom.solspring.domain.RecipeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeBoardRepository extends JpaRepository<RecipeBoard,Long> {
    Page<RecipeBoard> findByTitleContainingOrIntroContaining(String searchText1, String searchText2, Pageable pageable);
}
