package com.oldaim.routineproject.controller;

import com.oldaim.routineproject.Repository.MemberRepository;
import com.oldaim.routineproject.config.jwt.JwtAuthenticProvider;
import com.oldaim.routineproject.dto.MemberInfoDto;
import com.oldaim.routineproject.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class UserLoginController {
    final MemberRepository memberRepository;
    final PasswordEncoder passwordEncoder;
    final JwtAuthenticProvider jwtAuthenticationProvider;

    public UserLoginController(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtAuthenticProvider jwtAuthenticationProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @PostMapping("/join") // 회원가입
    public void join(@RequestBody MemberInfoDto dto){

        memberRepository.save(Member.builder()
                .memberId(dto.getMemberId())
                .memberPassword(passwordEncoder.encode(dto.getMemberPW()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

    }

    @PostMapping("/login") //로그인
    public void login(@RequestBody MemberInfoDto user, HttpServletResponse response) {
        Member member = memberRepository.findByMemberId(user.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.getMemberPW(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String token = jwtAuthenticationProvider.createToken(member.getUsername(), member.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);

        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

    }

    @PostMapping("/logout") //로그아웃
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
