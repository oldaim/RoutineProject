package com.oldaim.routineproject.entity.work;

import com.oldaim.routineproject.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class WishList extends ToDoList{


    @Builder
    public WishList(Long id, String content, CheckList checkList, WorkCategory workCategory, Member member) {
        super(id, content, checkList, workCategory, member);
    }
}
