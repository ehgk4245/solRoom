package com.solRoom.solspring.domain.recipeBoard;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Ingredient {
    String name;
    String amt;
}
