package com.solRoom.solspring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ImageOfBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long boardId;

    @Enumerated(EnumType.STRING)
    @Column
    private BoardType boardType;

    @Column(nullable = false)
    private String url;



}
