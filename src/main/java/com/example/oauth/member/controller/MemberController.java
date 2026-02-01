package com.example.oauth.member.controller;

import com.example.oauth.common.auth.JwtTokenProvider;
import com.example.oauth.member.domain.Member;
import com.example.oauth.member.dto.MemberCreateDto;
import com.example.oauth.member.dto.MemberLoginDto;
import com.example.oauth.member.dto.MemberLoginResDto;
import com.example.oauth.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberCreateDto dto){
        Member member = memberService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(member.getId());
    }
    @PostMapping("/doLogin")
    public MemberLoginResDto login(@RequestBody MemberLoginDto dto){
        Member member = memberService.login(dto);
        String accessToken = jwtTokenProvider.createToken(member);
        return MemberLoginResDto.builder()
                .access_token(accessToken)
                .refresh_token(null).build();
    }
}
