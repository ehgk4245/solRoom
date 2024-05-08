package com.solRoom.solspring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER) //Many = Board, One = Member
    @JoinColumn(name="memberId")
    private Member member;  // DB는 오브젝트를 저장할 수 없다. FK

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) // DB에 컬럼으로 만들어지지 않음
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;

}
