package com.example.instant_inting.exception;

public class NoAvailableMatchesException extends RuntimeException {
    public NoAvailableMatchesException(Long userId) {
        super("No available matches found for user with ID: " + userId);
    }
}
