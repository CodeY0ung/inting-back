package com.example.instant_inting.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PwUpdateDto {
    @NotBlank(message = "password는 필수 입력 정보 입니다.")
    private String password;

    @NotBlank(message = "새로운 password는 필수 입력 정보 입니다.")
    private String newPw;
}
