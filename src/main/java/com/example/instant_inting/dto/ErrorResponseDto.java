package com.example.instant_inting.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDto {
    private final int status;      // HTTP 상태 코드
    private final String error;    // 오류 유형
    private final String message;  // 상세 메시지
    private final String path;     // 요청 경로
    private final Map<String, String> errors; // ✅ 필드별 오류 메시지를 저장할 Map 추가

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now(); // 현재 시간 자동 설정
}
