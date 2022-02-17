package com.oldaim.routineproject.entity.work;

import com.oldaim.routineproject.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String content;

    @Column
    private CheckList checkList;

    @Column
    private  WorkCategory workCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;


    public ToDoList(Long id, String content, CheckList checkList, WorkCategory workCategory, Member member) {
        this.id = id;
        this.content = content;
        this.checkList = checkList;
        this.workCategory = workCategory;
        this.member = member;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void changeCheckListUndoToDo(){this.checkList = CheckList.DO;}

    public void changeCheckListDoToUndo(){this.checkList = CheckList.UNDO;}
}
