package com.solRoom.solspring.controller.dto;

import com.solRoom.solspring.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
public class MemberDTO {
    @Getter
    @Setter
    public static class RequestMemberDTO{
        private Long id;

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, message = "닉네임은 최소 2자 이상이어야 합니다.")
        private String nickname;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String password;

        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @NotBlank(message = "주소를 입력해주세요.")
        private String address;
        private String confirmPassword;


        /* 암호화된 password */
        public void encryptPassword(String BCryptpassword) {
            this.password = BCryptpassword;
        }

        /* DTO -> Entity */
        public Member toEntity() {
            return Member.builder()
                    .id(id)
                    .name(name)
                    .nickname(nickname)
                    .password(password)
                    .email(email)
                    .address(address)
                    .build();
        }

    }

}
