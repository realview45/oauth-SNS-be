package com.example.oauth.member.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@ToString
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    private String socialId;

}
