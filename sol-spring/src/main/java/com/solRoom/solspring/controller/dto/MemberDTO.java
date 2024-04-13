package com.solRoom.solspring.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String address;
    private String confirmPassword;

}
