package com.solRoom.solspring.domain.recipeBoard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CookingStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeBoard recipeBoard;

    @Column(nullable = false)
    private String description;

    @Lob
    @Column(nullable = false)
    private byte[] image;



}