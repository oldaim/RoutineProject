package com.oldaim.routineproject.service;


import com.oldaim.routineproject.Repository.MemberRepository;
import com.oldaim.routineproject.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final DailyToDoService dailyToDoService;
    private final WeeklyToDoService weeklyToDoService;
    private final WishListService wishListService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(username)
                .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }



    public List<Object> memberWork(Member member){
        Long findMemberId = member.getId();
        List<Object> memberWorkList = new ArrayList<>();

        memberWorkList.add(dailyToDoService.dailyFindAll(findMemberId));
        memberWorkList.add(weeklyToDoService.weeklyFindAll(findMemberId));
        memberWorkList.add(wishListService.wishFindAll(findMemberId));

        return memberWorkList;
    }



}
