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
public class DailyToDo extends ToDoList {

    @Column
    private Long startTime;

    @Column
    private Long endTime;

    @Column
    private Long resetTime;

    @Builder
    public DailyToDo(String content, CheckList checkList, Member member, Long startTime, Long endTime, Long resetTime) {
        super(content, checkList, member);
        this.startTime = startTime;
        this.endTime = endTime;
        this.resetTime = resetTime;
    }

}
