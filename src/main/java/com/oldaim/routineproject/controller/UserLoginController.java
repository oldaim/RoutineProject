package com.oldaim.routineproject.controller;

import com.oldaim.routineproject.Repository.MemberRepository;
import com.oldaim.routineproject.config.jwt.JwtAuthenticProvider;
import com.oldaim.routineproject.dto.MemberInfoDto;
import com.oldaim.routineproject.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserLoginController {

    private final MemberRepository memberRepository;
    private final JwtAuthenticProvider jwtAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/join") // 회원가입
    public void join(@RequestBody MemberInfoDto dto){

        memberRepository.save(Member.builder()
                .memberId(dto.getMemberId())
                .memberPassword(passwordEncoder.encode(dto.getMemberPW()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

    }

    @PostMapping("/login") //로그인
    public String login(@RequestBody MemberInfoDto dto, HttpServletResponse response) throws IOException {

        Member memberEntity = memberRepository.findByMemberId(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(dto.getMemberPW(), memberEntity.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String token = jwtAuthenticationProvider.createToken(memberEntity.getUsername(), memberEntity.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);
        //response.sendRedirect("http://localhost:8080/api/member/info");

       return token;

    }
    /*
    @PostMapping("/logout") //로그아웃
    public void logout(HttpServletResponse response){

        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

     */


}
