package com.solRoom.solspring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name ="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Column(nullable = false)
    private Long boardId;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    public enum BoardType{
        FREE,
        RECIPE,
        INTERIOR,
        BARTER
    }




}
