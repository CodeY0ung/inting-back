package com.example.instant_inting.service;

import com.example.instant_inting.domain.User;
import com.example.instant_inting.dto.LoginRequestDto;
import com.example.instant_inting.dto.LoginResponseDto;
import com.example.instant_inting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto){
        // user id로 사용자 정보 조회.
        User user = userRepository.findByUserId(loginRequestDto.getUserId())
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 사용자 입니다."));

        // 비밀번호가 맞지 않을 경우.
        if(!user.getPassword().equals(loginRequestDto.getPassword()))
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");

        // 로그인 성공시 loginResponse 정보 반환.
        return new LoginResponseDto().builder()
                .userId(user.getUserId())
                .instarId(user.getInstarId())
                .build();
    }

}
