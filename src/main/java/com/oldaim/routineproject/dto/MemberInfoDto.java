package com.oldaim.routineproject.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberInfoDto {

    private String memberId;

    private String memberPW;

    private final String auth = "USER";

    public void encryptPassword(String memberPW){
        this.memberPW = memberPW;
    }


}
