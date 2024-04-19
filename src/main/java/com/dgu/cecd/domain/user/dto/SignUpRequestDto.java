package com.dgu.cecd.domain.user.dto;

import com.dgu.cecd.domain.user.type.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "이메일을 반드시 입력해주세요.")
    @Email
    private String email;

    @NotBlank(message = "패스워드를 반드시 입력해주세요.")
    private String password;

    private String name;

    private RoleType role;
}
