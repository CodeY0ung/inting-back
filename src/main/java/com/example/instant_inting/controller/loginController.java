package com.example.instant_inting.controller;

import com.example.instant_inting.dto.LoginRequestDto;
import com.example.instant_inting.dto.LoginResponseDto;
import com.example.instant_inting.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class loginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        try {
            LoginResponseDto loginResponseDto = loginService.authenticate(loginRequestDto);
            return ResponseEntity.ok(loginResponseDto); // 성공시 사용자 정보 반환.
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // 실패시 상태코드 401 반환.
        }
    }
}
