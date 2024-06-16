package com.solRoom.solspring.domain.recipeBoard;

import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.domain.recipeBoard.CookingStep;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecipeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private int viewCount=0;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private int category1;   // 종류별

    @Column(nullable = false)
    private int category2;   // 상황별

    @Column(nullable = false)
    private int category3;   // 재료별

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="memberId")
    private Member member;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    @Column(nullable = false)
    private byte[] mainImage;   // 대표 사진

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @MapKeyColumn(name = "ingredient_name")
    @Column(name = "ingredient_amount")
    private Map<String, String> ingredients;

    @OneToMany(mappedBy = "recipeBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CookingStep> cookingSteps = new ArrayList<>();

    @ElementCollection
    @Column
    private List<String> dishImage = new ArrayList<>();


    private Timestamp createDate;


    public enum Difficulty {
        초급, 중급, 고급
    }






}
