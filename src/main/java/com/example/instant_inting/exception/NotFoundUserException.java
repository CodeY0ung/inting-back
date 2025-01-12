package com.example.instant_inting.exception;

import lombok.Getter;

@Getter
public class NotFoundUserException extends RuntimeException {
    private final String identifier;  // ✅ id 또는 userId를 String으로 저장
    private final String identifierType; // ✅ ID 유형 ("userId" or "id")

    public NotFoundUserException(Long id) {
        super("해당 id의 사용자를 찾을 수 없습니다: " + id);
        this.identifier = id.toString();
        this.identifierType = "id";
    }

    public NotFoundUserException(String userId) {
        super("해당 userId의 사용자를 찾을 수 없습니다: " + userId);
        this.identifier = userId;
        this.identifierType = "userId";
    }
}
