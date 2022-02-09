package com.oldaim.routineproject.dto;

import com.oldaim.routineproject.entity.work.CheckList;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WishListDto {

    private String content;

    private CheckList checkList;

}
