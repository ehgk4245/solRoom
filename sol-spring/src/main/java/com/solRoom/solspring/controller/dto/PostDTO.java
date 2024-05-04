package com.solRoom.solspring.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public Post toEntity()
}
