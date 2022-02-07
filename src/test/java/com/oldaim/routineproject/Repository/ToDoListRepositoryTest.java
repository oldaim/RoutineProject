package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.entity.work.CheckList;
import com.oldaim.routineproject.entity.work.DailyToDo;
import com.oldaim.routineproject.entity.work.ToDoList;
import com.oldaim.routineproject.entity.work.WeeklyToDo;
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
    @Autowired
    WeeklyToDoRepository weeklyToDoRepository;

    @Transactional
    @Test
    public void ToDoList_저장_테스트() throws Exception{

        //given
        Member member = Member.builder()
                .memberId("oldaim")
                .memberPassword("dgk0911")
                .build();

        DailyToDo toDoListDay = DailyToDo.builder()
                .content("hello")
                .checkList(CheckList.UNDO)
                .member(member)
                .startTime(1)
                .startMin(17)
                .endTime(15)
                .endMin(2)
                .build();

        WeeklyToDo toDoListWeek = WeeklyToDo.builder()
                .content("hello")
                .checkList(CheckList.UNDO)
                .member(member)
                .startTime(2)
                .startMin(1)
                .endTime(7)
                .endMin(5)
                .day("Monday")
                .build();
        //when
        memberRepository.save(member);
        dailyToDoRepository.save(toDoListDay);
        weeklyToDoRepository.save(toDoListWeek);

        //then

    }

}