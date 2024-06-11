package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.CookingStep;
import com.solRoom.solspring.domain.RecipeBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookingStepDTO {
    private Long id;
    private Long recipeBoardId;
    private String description;
    private byte[] image;

    public static CookingStepDTO fromEntity(CookingStep cookingStep) {
        return CookingStepDTO.builder()
                .id(cookingStep.getId())
                .recipeBoardId(cookingStep.getRecipeBoard().getId())
                .description(cookingStep.getDescription())
                .image(cookingStep.getImage())
                .build();
    }


    public CookingStep toEntity(RecipeBoard board) {
        return CookingStep.builder()
                .id(id)
                .recipeBoard(board)
                .description(description)
                .image(image)
                .build();
    }
}