package com.solRoom.solspring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(length = 100)
    private String password;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String confirmPassword;

//    @Enumerated(EnumType.STRING)
//    private GrantedAuthority role;






}
