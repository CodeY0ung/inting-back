package com.example.instant_inting.service;

import com.example.instant_inting.domain.User;
import com.example.instant_inting.dto.UserDto;
import com.example.instant_inting.exception.NotFoundUserException;
import com.example.instant_inting.repository.MatchRepository;
import com.example.instant_inting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    // userId로 회원 조회
    public UserDto findUserByUserId(String userId){
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new NotFoundUserException(userId));

        UserDto userDto = UserDto.builder()
                .instarId(user.getInstarId())
                .userId(user.getUserId())
                .build();

        return userDto;
    }

    // instarId로 회원 조회
    public UserDto findUserByInstarId(String instarId){
        User user = userRepository.findByInstarId(instarId)
                .orElseThrow(()-> new NotFoundUserException(instarId));

        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .instarId(user.getInstarId())
                .build();

        return userDto;
    }

    // 전체 user들 조회
    public List<UserDto> findAllUsers(){
        List<UserDto> userDtos = userRepository.findAll().stream()
                .map(user -> UserDto.builder()
                        .userId(user.getUserId())
                        .instarId(user.getInstarId())
                        .build())
                .collect(Collectors.toList());

        return userDtos;
    }
}
