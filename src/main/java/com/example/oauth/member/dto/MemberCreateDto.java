package com.example.oauth.member.dto;

import com.example.oauth.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCreateDto {
    private String email;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
