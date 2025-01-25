package com.example.instant_inting.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "matching") // 테이블 명 지정
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 적용
    private Long id;

    @CreationTimestamp
    private LocalDateTime matchDateTime; // 매칭 발생 시간 자동 저장

    // 매칭 요청을 한 사용자 (ManyToOne 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 매칭된 상대방 (ManyToOne 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matched_user_id", referencedColumnName = "id", nullable = false)
    private User matchedUser;

    @Column(nullable = false)
    private int priority; // 우선순위 (낮을수록 우선)
}
