package com.oldaim.routineproject.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WeeklyToDoDto {
    //content
    private String content;

    private String workCategory;
    //checkList
    private String checkList;
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
