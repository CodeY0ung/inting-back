package com.example.instant_inting.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class User {
    @Id
    @GeneratedValue
    private Long id;

    // userID 로그인 시 필요.
    @Column(nullable = false, unique = true)
    private String userId;

    // password 로그인 시 필요.
    @Column(nullable = false)
    private String password;

    // 가상 money 매칭 돌릴 때 필요.
    @Builder.Default
    private int coin = 0;

    // 인스타 id
    @Column(nullable = false)
    private String instarId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matching> matchings = new ArrayList<>();
}

