package com.example.oauth.member.service;

import com.example.oauth.member.domain.Member;
import com.example.oauth.member.dto.MemberCreateDto;
import com.example.oauth.member.dto.MemberLoginDto;
import com.example.oauth.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member create(MemberCreateDto dto) {
        return memberRepository.save(dto.toEntity(passwordEncoder.encode(dto.getPassword())));
    }

    public Member login(MemberLoginDto dto) {
        Optional<Member> member = memberRepository.findByEmail(dto.getEmail());
        boolean login = true;
        if(member.isPresent()){
            if(!passwordEncoder.matches(dto.getPassword(), member.get().getPassword())) {
                login = false;
            }
        }
        else{
            login=false;
        }
        if(!login){
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        return member.get();
    }
}
