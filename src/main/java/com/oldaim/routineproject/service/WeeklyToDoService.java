package com.oldaim.routineproject.service;

import com.oldaim.routineproject.Repository.WeeklyToDoRepository;
import com.oldaim.routineproject.dto.DailyToDoDto;
import com.oldaim.routineproject.dto.WeeklyToDoDto;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.entity.work.CheckList;
import com.oldaim.routineproject.entity.work.DailyToDo;
import com.oldaim.routineproject.entity.work.WeeklyToDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class WeeklyToDoService {

    private final WeeklyToDoRepository weeklyToDoRepository;
    //등록
    public Long weeklySave(Member member, WeeklyToDoDto dto){

        WeeklyToDo weeklyEntity = weeklyDtoToEntity(dto,member);

        weeklyToDoRepository.save(weeklyEntity);

        return weeklyEntity.getId();
    }

    public Long weeklyModify(WeeklyToDoDto dtoOld, WeeklyToDoDto dtoNew){

        WeeklyToDo weeklyToDo = weeklyToDoRepository.findByContent(dtoOld.getContent(),dtoOld.getStartTime()
                ,dtoOld.getStartMin(),dtoOld.getDay());

        weeklyToDo.changeWeeklyToDo(dtoNew.getContent(),dtoNew.getStartTime(),dtoNew.getStartMin()
                ,dtoNew.getEndTime(),dtoNew.getEndMin(),dtoNew.getDay());

        weeklyToDoRepository.save(weeklyToDo);

        return weeklyToDo.getId();

    }

    public List<WeeklyToDoDto> weeklyFindAll(Long id){
        List<WeeklyToDo> weeklyEntityList = weeklyToDoRepository.findAllByMember(id);
        List<WeeklyToDoDto> dtoList = new ArrayList<>();

        for (WeeklyToDo weekly : weeklyEntityList) {
            if(weekly.getCheckList() == CheckList.UNDO) {
                dtoList.add(weeklyEntityToDto(weekly));
            }
        }

        return dtoList;
    }

    public void weeklyDelete(WeeklyToDoDto dto){

        Long deleteEntityId = weeklyToDoRepository.findByContent(dto.getContent(),
                dto.getStartTime(),dto.getStartMin(),dto.getDay()).getId();

        weeklyToDoRepository.deleteById(deleteEntityId);
    }

    public void weeklyUndoToDo(WeeklyToDoDto dto){
        WeeklyToDo weeklyEntity = weeklyToDoRepository.findByContent(dto.getContent(),
                dto.getStartTime(), dto.getStartMin(),dto.getDay());

        weeklyEntity.changeCheckListUndoToDo();

        weeklyToDoRepository.save(weeklyEntity);
    }

    private WeeklyToDo weeklyDtoToEntity(WeeklyToDoDto dto, Member member){

        WeeklyToDo weeklyToDo = WeeklyToDo
                .builder()
                .content(dto.getContent())
                .checkList(CheckList.UNDO)
                .startTime(dto.getStartTime())
                .startMin(dto.getStartMin())
                .endTime(dto.getEndTime())
                .endMin(dto.getEndMin())
                .day(dto.getDay())
                .member(member)
                .build();

        return weeklyToDo;
    }

    private WeeklyToDoDto weeklyEntityToDto(WeeklyToDo weekly){

       WeeklyToDoDto dto = WeeklyToDoDto
               .builder()
               .content(weekly.getContent())
               .checkList(weekly.getCheckList())
               .startTime(weekly.getStartTime())
               .startMin(weekly.getStartMin())
               .endTime(weekly.getEndTime())
               .endMin(weekly.getEndMin())
               .day(weekly.getDay())
               .build();

        return dto;
    }
}
