package com.example.instant_inting.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserIdUpdateDto {
    @NotBlank(message = "기존 userId를 입력해주세요.")
    private String userId;

    @NotBlank(message = "새로운 userId를 입력해주세요.")
    private String newUserId;
}
