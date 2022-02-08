package com.oldaim.routineproject.service;

import com.oldaim.routineproject.Repository.DailyToDoRepository;
import com.oldaim.routineproject.Repository.MemberRepository;
import com.oldaim.routineproject.dto.DailyToDoDto;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.entity.work.CheckList;
import com.oldaim.routineproject.entity.work.DailyToDo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Transactional
@SpringBootTest
@Log4j2
class DailyToDoServiceTest {

    @Autowired
    DailyToDoService dailyToDoService;
    @Autowired
    DailyToDoRepository dailyToDoRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void Daily_저장_테스트(){

        //given
        Member memberDummy = Member
                .builder()
                .memberId("oldaim")
                .memberPassword("1111")
                .build();

        DailyToDoDto dtoDummy = DailyToDoDto
                .builder()
                .content("hello world")
                .checkList(CheckList.UNDO)
                .startTime(1)
                .startMin(30)
                .endTime(7)
                .endMin(30)
                .build();

        //when
        memberRepository.save(memberDummy);

        Long dummyId = dailyToDoService.dailySave(dtoDummy,memberDummy);

        Optional<DailyToDo> dummyEntity = dailyToDoRepository.findById(dummyId);

        //then
        if(dummyEntity.isPresent()) {
            assertThat(dummyEntity.get().getContent()).isEqualTo(dtoDummy.getContent());
        }
        else{
            fail("저장과정에 오류가 존재합니다.");
        }

    }

    @Test
    public void Daily_수정_테스트() throws Exception{

        //given
        Member memberDummy = Member
                .builder()
                .memberId("oldaim")
                .memberPassword("1111")
                .build();

        DailyToDoDto dtoOld = DailyToDoDto
                .builder()
                .content("hello world")
                .checkList(CheckList.UNDO)
                .startTime(1)
                .startMin(30)
                .endTime(7)
                .endMin(30)
                .build();

        DailyToDoDto dtoNew = DailyToDoDto
                .builder()
                .content("Not hello world")
                .checkList(CheckList.UNDO)
                .startTime(2)
                .startMin(30)
                .endTime(5)
                .endMin(30)
                .build();
        //when
        memberRepository.save(memberDummy);

        Long oldId = dailyToDoService.dailySave(dtoOld,memberDummy);

        Long newId = dailyToDoService.dailyModify(memberDummy,dtoOld,dtoNew);

        Optional<DailyToDo> dummyEntity = dailyToDoRepository.findById(newId);


        //then


        assertThat(newId).isEqualTo(oldId);
        if(dummyEntity.isPresent()){
            assertThat(dummyEntity.get().getContent()).isEqualTo(dtoNew.getContent());
        }
        else{
            fail("수정이 되지 않았습니다.");
        }

    }

    @Test
    public void daily_조회_삭제_테스트 () throws Exception{

        //given
        Member memberDummy = Member
                .builder()
                .memberId("oldaim")
                .memberPassword("1111")
                .build();

        memberRepository.save(memberDummy);

        IntStream.range(0, 10).mapToObj(i -> DailyToDoDto
                .builder()
                .content("hello world")
                .checkList(CheckList.UNDO)
                .startTime(1 + i)
                .startMin(30)
                .endTime(7 + i)
                .endMin(30)
                .build()).forEach(dtoDummy -> dailyToDoService.dailySave(dtoDummy, memberDummy));

        DailyToDoDto dtoAboutDelete = DailyToDoDto
                .builder()
                .content("hello world")
                .checkList(CheckList.UNDO)
                .startTime(1)
                .startMin(30)
                .endTime(7)
                .endMin(30)
                .build();

        //when
        List<DailyToDoDto> dtoList = dailyToDoService.dailyFindAll(memberDummy.getId());

        //then
        assertThat(dtoList.size()).isEqualTo(10);

        dailyToDoService.dailyDelete(dtoAboutDelete);

        dtoList = dailyToDoService.dailyFindAll(memberDummy.getId());

        assertThat(dtoList.size()).isEqualTo(9);

    }


}