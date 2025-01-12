package com.example.instant_inting.repository;

import com.example.instant_inting.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Matching, Long> {
    // 특정 유저가 이미 매칭한 SNS ID 조회
    @Query("SELECT m.matchedSnsId FROM Matching m WHERE m.user.id = :userId")
    List<String> findMatchedSnsIdsByUserId(@Param("userId") Long userId);

    List<Matching> findAllByUserId(Long userId);
}
