package com.oldaim.routineproject.controller;

import com.oldaim.routineproject.config.jwt.JwtAuthenticProvider;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final JwtAuthenticProvider jwtAuthenticationProvider;
    private final MemberService memberService;

    @PostMapping(value = "/info")
    public List<Object> findAllWorkByMember(@RequestHeader(value = "X-AUTH-TOKEN") String token){

        Member member = (Member) memberService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        return memberService.memberWork(member);
    }
}
