package com.example.instant_inting.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users") // 테이블 명 명확히 지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 적용
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId; // 유저 로그인 ID

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String instarId; // 인스타 ID

    @Column(nullable = false)
    @Builder.Default
    private int coin = 0; // 가상 머니 (기본값 0)

    // 매칭 내역 (일대다 관계)
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Matching> matchings = new ArrayList<>();
}
