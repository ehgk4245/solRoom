package com.solRoom.solspring.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FreeBoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=200)
    private String content;

    @ManyToOne //한 board에 여러개의 reply
    @JoinColumn(name="boardId")
    private FreeBoard board;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private String nickname;

    @CreationTimestamp
    private Timestamp createDate;
}
