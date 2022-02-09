package com.oldaim.routineproject.dto;

import com.oldaim.routineproject.entity.work.CheckList;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WeeklyToDoDto {
    //content
    private String content;
    //checkList
    private CheckList checkList;
    //startTime -Integer
    private Integer startTime;
    //startMin
    private Integer startMin;
    //endTime
    private Integer endTime;
    //endMin
    private Integer endMin;
    //day
    private String day;
}
