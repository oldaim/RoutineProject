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
    private Integer startTime;

    @Column
    private Integer startMin;

    @Column
    private Integer endTime;

    @Column
    private Integer endMin;

    @Builder
    public DailyToDo(Long id, String content, CheckList checkList, WorkCategory workCategory,
                     Member member, Integer startTime, Integer startMin, Integer endTime, Integer endMin) {
        super(id, content, checkList, workCategory, member);
        this.startTime = startTime;
        this.startMin = startMin;
        this.endTime = endTime;
        this.endMin = endMin;
    }




    public void changeDailyToDo(String content, Integer startTime, Integer startMin, Integer endTime, Integer endMin) {

        this.changeContent(content);
        this.startTime = startTime;
        this.startMin = startMin;
        this.endTime = endTime;
        this.endMin = endMin;
    }


}
