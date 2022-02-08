package com.oldaim.routineproject.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDto {
    private String memberId;

    private String memberPW;
}
