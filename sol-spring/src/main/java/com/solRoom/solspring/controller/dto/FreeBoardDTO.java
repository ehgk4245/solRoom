package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardDTO {
    private Long id;
    private String title;
    private String content;
    private int count;
    private Long memberId;
    private String memberNickname;
    private List<FreeBoardReplyDTO> replies;
    private Timestamp createDate;

    /* DTO -> Entity */
    public FreeBoard toEntity(Member member) {
        return FreeBoard.builder()
                .id(id)
                .title(title)
                .content(content)
                .count(count)
                .member(member)
                .createDate(createDate)
                .build();
    }
}
