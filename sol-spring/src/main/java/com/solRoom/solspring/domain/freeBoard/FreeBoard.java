package com.solRoom.solspring.domain.freeBoard;

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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int viewCount=0;

    private int likeCount=0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="memberId")
    private Member member;

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<FreeBoardReply> reply;

    @ElementCollection
    @Column
    private List<String> imageUrls = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createDate;

}
