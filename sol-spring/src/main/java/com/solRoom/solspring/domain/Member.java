package com.solRoom.solspring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;
    @Column(length = 100)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String address;

    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(EnumType.STRING)
    private RoleType role;


}
