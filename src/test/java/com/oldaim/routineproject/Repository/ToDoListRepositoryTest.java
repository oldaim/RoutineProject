package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.entity.work.CheckList;
import com.oldaim.routineproject.entity.work.DailyToDo;
import com.oldaim.routineproject.entity.work.ToDoList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ToDoListRepositoryTest {
    @Autowired
    DailyToDoRepository dailyToDoRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void ToDoList_저장_테스트() throws Exception{

        //given
        Member member = Member.builder()
                .memberId("oldaim")
                .memberPassword("dgk0911")
                .build();

        DailyToDo toDoList = DailyToDo.builder()
                .content("ss")
                .checkList(CheckList.UNDO)
                .startTime(1L)
                .endTime(1L)
                .resetTime(1L)
                .member(member)
                .build();

        //when
        memberRepository.save(member);
        dailyToDoRepository.save(toDoList);

        //then

    }

}