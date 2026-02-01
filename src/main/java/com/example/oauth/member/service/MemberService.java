package com.example.oauth.member.service;

import com.example.oauth.member.domain.Member;
import com.example.oauth.member.dto.MemberCreateDto;
import com.example.oauth.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(MemberCreateDto dto) {
        memberRepository.save(dto.toEntity());
    }
}
