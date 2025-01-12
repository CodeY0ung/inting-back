package com.example.instant_inting.repository;

import com.example.instant_inting.domain.User;
import com.example.instant_inting.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // interface 메서드는 자동으로 public으로 간주됨
    boolean existsByUserId(String userId);
    boolean existsByInstarId(String isntarId);

    // userId로 회원 조회
    Optional<User> findByUserId(String userId);

    // instarId로 회원 조회
    Optional<User> findByInstarId(String instarId);
}
