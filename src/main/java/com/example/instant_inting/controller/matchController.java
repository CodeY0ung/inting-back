package com.example.instant_inting.controller;

import com.example.instant_inting.domain.Matching;
import com.example.instant_inting.domain.User;
import com.example.instant_inting.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class matchController {

    private final MatchService matchService;

    /**
     * 사용자 매칭 요청 API
     */
    @PostMapping("/{userId}")
    public ResponseEntity<User> match(@PathVariable Long userId) {
        try {
            User matchedUser = matchService.matchUser(userId);
            return ResponseEntity.ok(matchedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * 사용자의 매칭 히스토리 조회 API (페이징 지원)
     */
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Matching>> getMatchHistory(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<Matching> history = matchService.getMatchHistory(userId, page, size);
        return ResponseEntity.ok(history);
    }
}
