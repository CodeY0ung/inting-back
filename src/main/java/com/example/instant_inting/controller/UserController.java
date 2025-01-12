package com.example.instant_inting.controller;

import com.example.instant_inting.dto.JoinDto;
import com.example.instant_inting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/join")
    public ResponseEntity<String> join(@Validated @RequestBody JoinDto joinDto){
        userService.registerUser(joinDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // id로 회원 삭제
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build(); // 별도의 응답 메세지 필요 없을 경우. 204
    }

}
