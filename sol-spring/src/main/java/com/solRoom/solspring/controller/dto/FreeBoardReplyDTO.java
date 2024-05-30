package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.domain.FreeBoardReply;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardReplyDTO {
    private Long id;
    @NotBlank(message = "댓글 내용을 입력해주세요!")
    private String content;
    private Long boardId;
    private Long memberId;
    private String nickname;
    private Timestamp createDate;

    /* DTO -> Entity */
    public FreeBoardReply toEntity(FreeBoard board, Member member){
        return FreeBoardReply.builder()
                .id(id)
                .content(content)
                .board(board)
                .member(member)
                .nickname(member.getNickname())
                .createDate(createDate)
                .build();
    }
}
