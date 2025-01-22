package com.example.instant_inting.controller;

import com.example.instant_inting.dto.InstarIdUpdateDto;
import com.example.instant_inting.dto.JoinDto;
import com.example.instant_inting.dto.PwUpdateDto;
import com.example.instant_inting.dto.UserIdUpdateDto;
import com.example.instant_inting.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> join(@Valid @RequestBody JoinDto joinDto){
        userService.registerUser(joinDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // id로 회원 삭제
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build(); // 별도의 응답 메세지 필요 없을 경우. 204
    }

    // password 변경
    @PatchMapping("/user/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,
                                            @RequestBody @Valid PwUpdateDto pwUpdateDto){
        userService.updatePw(id, pwUpdateDto);
        return ResponseEntity.noContent().build();
    }

    // userId 변경
    @PatchMapping("/user{id}/user_id")
    public ResponseEntity<Void> updateUserId(@PathVariable Long id,
                                             @RequestBody @Valid UserIdUpdateDto userIdUpdateDto){
        userService.updateUserId(id,userIdUpdateDto);
        return ResponseEntity.noContent().build();
    }

    // instarId 변경
    @PatchMapping("/user/{id}/instar_id")
    public ResponseEntity<Void> updateInstarId(@PathVariable Long id,
                                               @RequestBody @Valid InstarIdUpdateDto instarIdUpdateDto){
        userService.updateInstarId(id,instarIdUpdateDto);
        return ResponseEntity.noContent().build();
    }

}
