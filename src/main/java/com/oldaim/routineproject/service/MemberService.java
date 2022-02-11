package com.oldaim.routineproject.service;


import com.oldaim.routineproject.Repository.MemberRepository;
import com.oldaim.routineproject.dto.MemberInfoDto;
import com.oldaim.routineproject.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(username)
                .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public void memberSignUp(MemberInfoDto dto){
        memberRepository.save(Member.builder()
                .memberId(dto.getMemberId())
                .memberPassword(passwordEncoder.encode(dto.getMemberPW()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }

    public Member memberLogin(MemberInfoDto dto){

        Member memberEntity = memberRepository.findByMemberId(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(dto.getMemberPW(), memberEntity.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return memberEntity;
    }



}
