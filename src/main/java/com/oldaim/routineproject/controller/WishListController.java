package com.oldaim.routineproject.controller;

import com.oldaim.routineproject.config.jwt.JwtAuthenticProvider;
import com.oldaim.routineproject.dto.WishListDto;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.service.MemberService;
import com.oldaim.routineproject.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wish")
@RequiredArgsConstructor
public class WishListController {

    private final JwtAuthenticProvider jwtAuthenticationProvider;
    private final MemberService memberService;
    private final WishListService wishListService;

    @PostMapping(value = "/register",headers = "Header")
    public void wishRegister(@RequestBody WishListDto dto, @RequestHeader(value = "X-AUTH-TOKEN") String token){

        Member member = (Member) memberService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        wishListService.wishSave(member,dto);

    }

    @PostMapping(value = "/modify")
    public void wishModify(@RequestBody List<WishListDto> dtoList){

        wishListService.wishModify(dtoList.get(0),dtoList.get(1));

    }

    @PostMapping("/delete")
    public void wishDelete(@RequestBody WishListDto dto){
        wishListService.wishDelete(dto);
    }

    @PostMapping("/changeStatus")
    public void wishChangeStatus(@RequestBody WishListDto dto){wishListService.wishListUndoToDo(dto);}
}
