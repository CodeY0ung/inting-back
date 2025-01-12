package com.example.instant_inting.controller;

import com.example.instant_inting.domain.Matching;
import com.example.instant_inting.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
public class matchController {

    private final MatchService matchService;

    @GetMapping("/match")
    public ResponseEntity<?> match(@RequestParam Long userId) {
        return matchService.executeMatch(userId)
                .map(matchedSnsId -> ResponseEntity.ok("Matched with: " + matchedSnsId))
                .orElse(ResponseEntity.badRequest().body("No match available"));
    }

    @GetMapping("/users/{userId}/history")
    public ResponseEntity<List<Matching>> getMatchHistory(@PathVariable Long userId) {
        List<Matching> history = matchService.getMatchHistory(userId);
        return ResponseEntity.ok(history);
    }
}
