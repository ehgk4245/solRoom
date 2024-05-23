package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

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

    /* Entity -> DTO */
    public FreeBoardDTO(FreeBoard board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.count = board.getCount();
        this.memberId = board.getMember().getId();
        this.memberNickname = board.getMember().getNickname();
        this.createDate = board.getCreateDate();
    }
}

