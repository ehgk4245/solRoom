package com.solRoom.solspring.domain.barterBoard;

import com.solRoom.solspring.domain.BoardType;
import com.solRoom.solspring.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BarterBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int viewCount=0;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(nullable = false)
    private Long category;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeType tradeType;    // 기부, 교환

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemCondition itemCondition;    // 기부, 교환

    @Column(nullable = false)
    private String priceRange;  // 가격대

    @ElementCollection
    @Column
    private List<String> imageUrls = new ArrayList<>();

    @Column(nullable = false)
    private String location;

    @CreationTimestamp
    private Timestamp createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="memberId")
    private Member member;

    private String nickName;

    public enum TradeType {
        DONATION,
        EXCHANGE
    }
    public enum ItemCondition {
        USED,
        NEW
    }


}

