package com.example.instant_inting.exception;

import com.example.instant_inting.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ NotFoundUserException 처리
    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundUserException(NotFoundUserException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "User Not Found", e.getMessage(), request.getRequestURI(), null);
    }

    // ✅ NoAvailableMatchesException 처리 (새로운 예외 핸들러 추가)
    @ExceptionHandler(NoAvailableMatchesException.class)
    public ResponseEntity<ErrorResponseDto> handleNoAvailableMatchesException(NoAvailableMatchesException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "No Available Matches", e.getMessage(), request.getRequestURI(), null);
    }

    // ✅ IllegalArgumentException (잘못된 입력값) 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Request", e.getMessage(), request.getRequestURI(), null);
    }

    // ✅ DTO 유효성 검사 실패 시 400 에러 반환
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        // 필드별 오류 메시지를 저장할 Map 생성
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Dto validation error", "유효성 검사 실패", request.getRequestURI(), errors);
    }

    // ✅ 기타 모든 예외 처리 (서버 에러 등)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e.getMessage(), request.getRequestURI(), null);
    }

    // ✅ 공통 에러 응답 생성 메서드
    private ResponseEntity<ErrorResponseDto> buildErrorResponse(HttpStatus status, String error, String message, String path, Map<String , String> errors) {
        return ResponseEntity.status(status)
                .body(new ErrorResponseDto(status.value(), error, message, path, errors));
    }
}
