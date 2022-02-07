package com.oldaim.routineproject.entity;

import com.oldaim.routineproject.entity.work.ToDoList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Long id;

    @Column
    private String memberId;

    @Column
    private String memberPassword;

    @Column
    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<ToDoList> toDoList;

    @Builder
    public Member(Long id, String memberId, String memberPassword, List<ToDoList> toDoList) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.toDoList = toDoList;
    }
}
