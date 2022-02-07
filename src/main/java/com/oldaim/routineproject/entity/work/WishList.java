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
    public WishList(String content, CheckList checkList, Member member) {
        super(content, checkList, member);
    }

}
