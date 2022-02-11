package com.oldaim.routineproject.controller;

import com.oldaim.routineproject.config.jwt.JwtAuthenticProvider;
import com.oldaim.routineproject.dto.MemberInfoDto;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserLoginController {

    private final MemberService memberService;
    private final JwtAuthenticProvider jwtAuthenticationProvider;



    @PostMapping("/join") // 회원가입
    public void join(@RequestBody MemberInfoDto dto){

        memberService.memberSignUp(dto);

    }

    @PostMapping("/login") //로그인
    public void login(@RequestBody MemberInfoDto dto, HttpServletResponse response) {

        Member member = memberService.memberLogin(dto);

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
