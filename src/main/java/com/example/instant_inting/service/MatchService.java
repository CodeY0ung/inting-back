package com.example.instant_inting.service;

import com.example.instant_inting.domain.Matching;
import com.example.instant_inting.domain.User;
import com.example.instant_inting.repository.MatchRepository;
import com.example.instant_inting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public Optional<String> executeMatch(Long userId) {
        // 현재 유저를 제외한 유저 목록
        List<User> potentialMatches = userRepository.findAll();
        potentialMatches.removeIf(user -> user.getId().equals(userId));

        // 이미 매칭된 유저의 SNS ID 목록
        List<String> alreadyMatchedIds = matchRepository.findMatchedSnsIdsByUserId(userId);

        // 이미 매칭된 유저 제외
        potentialMatches.removeIf(user -> alreadyMatchedIds.contains(user.getInstarId()));

        // 매칭 가능한 유저가 없는 경우
        if (potentialMatches.isEmpty()) {
            return Optional.empty();
        }

        // 랜덤으로 유저 선택
        int randomIndex = ThreadLocalRandom.current().nextInt(potentialMatches.size());
        User matchedUser = potentialMatches.get(randomIndex);

        // 매칭 기록 저장
        saveMatch(userId, matchedUser.getInstarId());

        // 매칭된 유저의 인스타그램 ID 반환
        return Optional.of(matchedUser.getInstarId());
    }

    private void saveMatch(Long userId, String matchedSnsId) {
        Matching matching = Matching.builder()
                .user(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found")))
                .matchedSnsId(matchedSnsId)
                .build();

        matchRepository.save(matching);
    }

    public List<Matching> getMatchHistory(Long userId) {
        return matchRepository.findAllByUserId(userId);
    }
}
