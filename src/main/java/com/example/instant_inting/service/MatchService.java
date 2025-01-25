package com.example.instant_inting.service;

import com.example.instant_inting.domain.Matching;
import com.example.instant_inting.domain.User;
import com.example.instant_inting.exception.NotFoundUserException;
import com.example.instant_inting.exception.NoAvailableMatchesException;
import com.example.instant_inting.repository.MatchRepository;
import com.example.instant_inting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    /**
     * 사용자 매칭 요청 로직
     */
    @Transactional
    public User matchUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException(userId));

        // 기존 매칭된 사용자 목록 조회 (최근 20개만 가져오도록 제한)
        Pageable recentMatchesLimit = PageRequest.of(0, 20);
        List<User> previousMatches = matchRepository.findMatchedUsersByUserId(userId, recentMatchesLimit);

        // 새로운 매칭 후보 조회 (이전에 매칭되지 않은 사용자)
        Pageable pageable = PageRequest.of(0, 5);
        List<User> potentialMatches = userRepository.findPotentialMatches(userId, pageable);

        // 매칭할 사용자 결정
        User matchedUser;
        if (potentialMatches.isEmpty()) {
            if (previousMatches.isEmpty()) {
                throw new NoAvailableMatchesException(userId);
            }
            matchedUser = previousMatches.get(0); // 이전 매칭된 사용자 중 우선순위가 가장 높은 사람과 매칭
        } else {
            // 랜덤 매칭 (최적화: 리스트 크기가 1이면 바로 선택)
            matchedUser = (potentialMatches.size() == 1)
                    ? potentialMatches.get(0)
                    : potentialMatches.get(ThreadLocalRandom.current().nextInt(potentialMatches.size()));
        }

        // 새로운 매칭 저장
        Matching newMatch = Matching.builder()
                .user(user)
                .matchedUser(matchedUser)
                .matchDateTime(LocalDateTime.now())
                .priority(0) // 새 매칭은 우선순위 0으로 설정
                .build();

        matchRepository.save(newMatch); // 🔥 매칭 먼저 저장

        // 기존 매칭 기록의 우선순위 증가 (배치 업데이트 적용)
        matchRepository.incrementPriorityEfficiently(userId);

        return matchedUser;
    }

    /**
     * 사용자 매칭 히스토리 조회 (페이징 적용)
     */
    public List<Matching> getMatchHistory(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return matchRepository.findByUserIdOrderByMatchDateTimeDesc(userId, pageable);
    }
}
