package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.BarterBoard;
import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.Category;
import com.solRoom.solspring.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarterBoardDTO {
    private Long id;
    private Long memberId;
    private String nickName;
    private int viewCount;
    private String boardType;

    @NotBlank(message = "카테고리를 선택해주세요")
    private String category;

    @NotBlank(message = "상품명을 입력해주세요")
    @Size(max = 30, message = "최대 30글자로 입력해주세요")
    private String productName;

    @NotBlank(message = "상품을 설명해주세요")
    private String content;

    private String tradeType; // DONATION, EXCHANGE

    private String itemCondition; // USED, NEW

    @Builder.Default
    private String priceRange = "0";

//    @NotEmpty(message = "상품 사진을 넣어주세요")
    private List<String> imageUrls;

    @NotBlank(message = "거래 장소를 입력해주세요")
    private String location;
    private Timestamp createDate;

    public BarterBoard toEntity(Member member,Category category){
        return BarterBoard.builder()
                .id(id)
                .member(member)
                .nickName(member.getNickname())
                .viewCount(viewCount)
                .boardType(BoardType.BARTER)
                .category(category)
                .productName(productName)
                .content(content)
                .tradeType(BarterBoard.TradeType.valueOf(tradeType))
                .itemCondition(BarterBoard.ItemCondition.valueOf(itemCondition))
                .priceRange(priceRange)
                .imageUrls(new ArrayList<>())
                .location(location)
                .createDate(createDate)
                .build();

    }

    // 엔티티 -> DTO
    public static BarterBoardDTO fromEntity(BarterBoard barterBoard) {
        return BarterBoardDTO.builder()
                .id(barterBoard.getId())
                .memberId(barterBoard.getMember().getId())
                .nickName(barterBoard.getMember().getNickname())
                .viewCount(barterBoard.getViewCount())
                .boardType(barterBoard.getBoardType().toString()) // Convert Enum to String
                .category(barterBoard.getCategory().getName())
                .productName(barterBoard.getProductName())
                .content(barterBoard.getContent())
                .tradeType(barterBoard.getTradeType().toString()) // Convert Enum to String
                .itemCondition(barterBoard.getItemCondition().toString()) // Convert Enum to String
                .priceRange(barterBoard.getPriceRange())
                .imageUrls(barterBoard.getImageUrls())
                .location(barterBoard.getLocation())
                .createDate(barterBoard.getCreateDate())
                .build();
    }

}
