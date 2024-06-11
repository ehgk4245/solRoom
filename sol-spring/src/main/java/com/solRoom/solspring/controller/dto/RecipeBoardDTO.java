package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.CookingStep;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.domain.RecipeBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeBoardDTO {
    private Long id;
    private String nickName;
    private int viewCount;
    private Timestamp createDate;
    private String boardType;
    private String title;
    private String intro;
    private int category1;
    private int category2;
    private int category3;
    private Long memberId;
    private String difficulty;
    private byte[] mainImage;
    private Map<String, String> ingredients;     // 기본 값 초기화
    private List<CookingStepDTO> cookingSteps = new ArrayList<>(); // 기본 값 초기화
    private List<String> dishImage = new ArrayList<>(); // 기본 값 초기화

    public RecipeBoard toEntity(Member member) {
        RecipeBoard recipeBoard = RecipeBoard.builder()
                .id(id)
                .nickName(nickName)
                .viewCount(viewCount)
                .boardType(BoardType.RECIPE)
                .title(title)
                .intro(intro)
                .category1(category1)
                .category2(category2)
                .category3(category3)
                .member(member)
                .difficulty(RecipeBoard.Difficulty.valueOf(difficulty))
                .mainImage(mainImage)
                .createDate(createDate)
                .ingredients(ingredients)
//                .dishImage(dishImage)
                .build();

        // CookingSteps 설정
        List<CookingStep> cookingStepEntities = cookingSteps.stream()
                .map(dto -> dto.toEntity(recipeBoard))
                .collect(Collectors.toList());
        recipeBoard.setCookingSteps(cookingStepEntities);

        return recipeBoard;
    }

    public static RecipeBoardDTO fromEntity(RecipeBoard recipeBoard) {
        return RecipeBoardDTO.builder()
                .id(recipeBoard.getId())
                .nickName(recipeBoard.getNickName())
                .viewCount(recipeBoard.getViewCount())
                .createDate(recipeBoard.getCreateDate())
                .boardType(recipeBoard.getBoardType().toString())
                .title(recipeBoard.getTitle())
                .intro(recipeBoard.getIntro())
                .category1(recipeBoard.getCategory1())
                .category2(recipeBoard.getCategory2())
                .category3(recipeBoard.getCategory3())
                .memberId(recipeBoard.getMember().getId())
                .difficulty(recipeBoard.getDifficulty().toString())
                .mainImage(recipeBoard.getMainImage())
                .ingredients(recipeBoard.getIngredients())
                .cookingSteps(recipeBoard.getCookingSteps().stream()
                        .map(CookingStepDTO::fromEntity)
                        .collect(Collectors.toList()))
                .dishImage(recipeBoard.getDishImage())
                .build();
    }
}
