package com.oldaim.routineproject.entity.work;

import com.oldaim.routineproject.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor

public class WeeklyToDo extends ToDoList{
    @Column
    private Integer startTime;

    @Column
    private Integer startMin;

    @Column
    private Integer endTime;

    @Column
    private Integer endMin;

    @Column
    private String day;

    @Builder
    public WeeklyToDo(String content, CheckList checkList, Member member,
                      Integer startTime, Integer startMin, Integer endTime, Integer endMin, String day) {
        super(content, checkList, member);
        this.startTime = startTime;
        this.startMin = startMin;
        this.endTime = endTime;
        this.endMin = endMin;
        this.day = day;
    }
}
