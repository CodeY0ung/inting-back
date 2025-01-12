package com.example.instant_inting.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class JoinDto {
    @NotBlank(message = "아이디는 필수 입력 값 입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    private String password;

    @NotBlank(message = "Instar ID는 필수 입력 값 입니다.")
    private String instarId;
}
