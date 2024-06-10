package com.solRoom.solspring.domain.mallDomain;

import com.solRoom.solspring.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Products product;

    @Column(nullable = false)
    private Timestamp orderDate;

    @Column(nullable = false)
    private String deliveryAddress;

}
