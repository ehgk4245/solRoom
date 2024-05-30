package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.BarterBoard;
import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardDTO {
    private Long id;
    private String boardType;
    private String title;
    private String content;
    private int viewCount;
    private int likeCount;
    private Long memberId;
    private String nickname;
    private List<FreeBoardReplyDTO> replies; // 추가된 필드
    private List<String> imageUrls;
    private Timestamp createDate;

    /* DTO -> Entity */
    public FreeBoard toEntity(Member member) {
        return FreeBoard.builder()
                .id(id)
                .boardType(BoardType.FREE)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .likeCount(likeCount)
                .member(member)
                .imageUrls(new ArrayList<>())
                .createDate(createDate)
                .build();
    }
    /* Entity -> DTO */
    public static FreeBoardDTO fromEntity(FreeBoard freeBoard) {
        return FreeBoardDTO.builder()
                .id(freeBoard.getId())
                .nickname(freeBoard.getMember().getNickname())
                .boardType(freeBoard.getBoardType().toString()) // Convert Enum to String
                .title(freeBoard.getTitle())
                .content(freeBoard.getContent())
                .viewCount(freeBoard.getViewCount())
                .likeCount(freeBoard.getLikeCount())
                .memberId(freeBoard.getMember().getId())
                .imageUrls(freeBoard.getImageUrls())
                .createDate(freeBoard.getCreateDate())
                .build();
    }
}
