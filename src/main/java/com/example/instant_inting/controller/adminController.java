package com.example.instant_inting.controller;

import com.example.instant_inting.dto.UserDto;
import com.example.instant_inting.repository.MatchRepository;
import com.example.instant_inting.repository.UserRepository;
import com.example.instant_inting.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class adminController {
    private final AdminService adminService;

    // 전체 회원 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findUsesrs(){
        List<UserDto> userDtos = adminService.findAllUsers();
        return ResponseEntity.ok(userDtos);
    }

    // 회원 id로 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> findUserByUserId(@PathVariable("userId") String userId){
        UserDto userDto = adminService.findUserByUserId(userId);
        return ResponseEntity.ok(userDto);
    }

    // 인스타 id로 조회
    @GetMapping("user/instar/{instarId}")
    public ResponseEntity<UserDto> findUserByInstarId(@PathVariable("instarId") String instarId){
        UserDto userDto = adminService.findUserByInstarId(instarId);
        return ResponseEntity.ok(userDto);
    }

    // 전체 매칭 기록 조회
}
