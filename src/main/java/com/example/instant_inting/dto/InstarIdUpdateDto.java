package com.example.instant_inting.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class InstarIdUpdateDto {
    @NotBlank(message = "기존 instar id를 입력해주세요.")
    private String instarId;
    @NotBlank(message = "새로운 instar id를 입력해주세요.")
    private String newInstarId;
}
