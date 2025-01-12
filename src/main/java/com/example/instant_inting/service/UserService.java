package com.example.instant_inting.service;

import com.example.instant_inting.domain.User;
import com.example.instant_inting.dto.JoinDto;
import com.example.instant_inting.exception.NotFoundUserException;
import com.example.instant_inting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원 가입
    public void registerUser(JoinDto joinDto){
        // id 중복 확인
        if(userRepository.existsByUserId(joinDto.getUserId())){
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }

        // instar id 중복 확인
        if(userRepository.existsByInstarId(joinDto.getInstarId())){
            throw new IllegalStateException("이미 존재하는 인스타 아이디 입니다.");
        }
        User user = User.builder()
                .userId(joinDto.getUserId())
                .instarId(joinDto.getInstarId())
                .password(joinDto.getPassword())
                .build();

        // 회원 정보 저장.
        userRepository.save(user);
    }

    // id로 회원 삭제
    public void deleteById(Long id){
        if (!userRepository.existsById(id)){
            throw new NotFoundUserException(id);
        }
        userRepository.deleteById(id);
    }
}
