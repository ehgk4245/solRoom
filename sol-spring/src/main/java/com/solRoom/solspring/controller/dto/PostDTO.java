package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.Post;
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

    public Post toEntity(){
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
