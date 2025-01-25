package com.example.instant_inting.repository;

import com.example.instant_inting.domain.Matching;
import com.example.instant_inting.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Matching, Long> {

    /**
     * 🔹 최근 매칭된 사용자 목록 조회 (최근 20개만 가져오도록 페이징 적용)
     */
    @Query("SELECT m.matchedUser FROM Matching m WHERE m.user.id = :userId ORDER BY m.matchDateTime DESC")
    List<User> findMatchedUsersByUserId(@Param("userId") Long userId, Pageable pageable);

    /**
     * 🔹 기존 매칭된 사용자들의 우선순위 증가 (배치 업데이트 적용)
     * - priority 값이 10 이상이면 더 이상 증가하지 않도록 최적화
     */
    @Transactional
    @Modifying
    @Query("UPDATE Matching m SET m.priority = m.priority + 1 WHERE m.user.id = :userId AND m.priority < 10")
    void incrementPriorityEfficiently(@Param("userId") Long userId);

    /**
     * 🔹 사용자의 매칭 히스토리 조회 (최신순 정렬 & 페이징 적용)
     */
    @Query("SELECT m FROM Matching m WHERE m.user.id = :userId ORDER BY m.matchDateTime DESC")
    List<Matching> findByUserIdOrderByMatchDateTimeDesc(@Param("userId") Long userId, Pageable pageable);
}
