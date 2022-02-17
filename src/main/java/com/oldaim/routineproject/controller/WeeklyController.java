package com.oldaim.routineproject.controller;

import com.oldaim.routineproject.config.jwt.JwtAuthenticProvider;
import com.oldaim.routineproject.dto.WeeklyToDoDto;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.service.MemberService;
import com.oldaim.routineproject.service.WeeklyToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weekly")
@RequiredArgsConstructor
public class WeeklyController {

    private final JwtAuthenticProvider jwtAuthenticationProvider;
    private final MemberService memberService;
    private final WeeklyToDoService weeklyToDoService;

    @PostMapping(value = "/register")
    public void weeklyRegister(@RequestBody WeeklyToDoDto dto,@RequestHeader(value = "X-AUTH-TOKEN") String token ){

        Member member = (Member) memberService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        weeklyToDoService.weeklySave(member,dto);

    }

    @PostMapping(value = "/modify")
    public void weeklyModify(@RequestBody List<WeeklyToDoDto> dtoList){
        weeklyToDoService.weeklyModify(dtoList.get(0),dtoList.get(1));
    }

    @PostMapping("/delete")
    public void weeklyDelete(@RequestBody WeeklyToDoDto dto){
        weeklyToDoService.weeklyDelete(dto);
    }

    @PostMapping("/changStatus")
    public void weeklyChangeStatus(@RequestBody WeeklyToDoDto dto){weeklyToDoService.weeklyUndoToDo(dto);}
}
