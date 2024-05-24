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
    private int viewCount;
    private int likeCount;
    private Long memberId;
    private String nickname;
    private List<FreeBoardReplyDTO> replies;
    private Timestamp createDate;

    /* DTO -> Entity */
    public FreeBoard toEntity(Member member) {
        return FreeBoard.builder()
                .id(id)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .likeCount(likeCount)
                .member(member)
                .createDate(createDate)
                .build();
    }
    /* Entity -> DTO */
    public FreeBoardDTO(FreeBoard board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCount = board.getViewCount();
        this.memberId = board.getMember().getId();
        this.nickname = board.getMember().getNickname();
        this.createDate = board.getCreateDate();
    }
}
