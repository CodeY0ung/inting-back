package com.example.instant_inting.repository;

import com.example.instant_inting.domain.User;
import com.example.instant_inting.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * 🔹 새로운 매칭 후보 찾기 (이전에 매칭된 사용자는 제외)
     * - 기존에는 `previousMatches` 리스트를 직접 전달했지만, `LEFT JOIN`을 사용하여 매칭된 사용자를 자동 필터링함.
     */
    @Query("SELECT u FROM User u " +
            "LEFT JOIN Matching m ON u.id = m.matchedUser.id AND m.user.id = :userId " +
            "WHERE u.id != :userId AND m.id IS NULL")
    List<User> findPotentialMatches(@Param("userId") Long userId, Pageable pageable);
}
