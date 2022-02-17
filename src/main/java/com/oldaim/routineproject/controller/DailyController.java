package com.oldaim.routineproject.controller;

import com.oldaim.routineproject.config.jwt.JwtAuthenticProvider;
import com.oldaim.routineproject.dto.DailyToDoDto;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.service.DailyToDoService;
import com.oldaim.routineproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/daily")
public class DailyController {

    private final JwtAuthenticProvider jwtAuthenticationProvider;
    private final MemberService memberService;
    private final DailyToDoService dailyToDoService;

    @PostMapping(value = "/register")
    public void dailyRegister(@RequestBody DailyToDoDto dto,@RequestHeader(value = "X-AUTH-TOKEN") String token ){

        Member member = (Member) memberService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        dailyToDoService.dailySave(dto,member);

    }

    @PostMapping(value = "/modify")
    public void dailyModify(@RequestBody List<DailyToDoDto> dtoList){

        dailyToDoService.dailyModify(dtoList.get(0),dtoList.get(1));

    }

    @PostMapping("/delete")
    public void dailyDelete(@RequestBody DailyToDoDto dto){
        dailyToDoService.dailyDelete(dto);
    }

    @PostMapping("/changeStatus")
    public void dailyChangStatus(@RequestBody DailyToDoDto dto){dailyToDoService.dailyUndoToDo(dto);}
}
