package com.oldaim.routineproject.service;

import com.oldaim.routineproject.Repository.DailyToDoRepository;
import com.oldaim.routineproject.dto.DailyToDoDto;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.entity.work.CheckList;
import com.oldaim.routineproject.entity.work.DailyToDo;
import com.oldaim.routineproject.entity.work.WorkCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class DailyToDoService {
    private final DailyToDoRepository dailyToDoRepository;
    //dto -> Entity 로 변경

    //등록
    public Long dailySave(DailyToDoDto dto, Member member){

        DailyToDo entity = dailyDtoToEntity(dto,member);

        dailyToDoRepository.save(entity);

        return entity.getId();

    }
    //수정
    public Long dailyModify(DailyToDoDto dtoOld, DailyToDoDto dtoNew){

       DailyToDo dailyToDo =  dailyToDoRepository.findByContent(dtoOld.getContent()
               ,dtoOld.getStartTime(),dtoOld.getStartMin());

       dailyToDo.changeDailyToDo(dtoNew.getContent(), dtoNew.getStartTime(),
               dtoNew.getStartMin(),dtoNew.getEndTime(),dtoNew.getEndMin());

       dailyToDoRepository.save(dailyToDo);

       log.info(dailyToDo);

       return dailyToDo.getId();
    }
    //조회
    public List<DailyToDoDto> dailyFindAll(Long id){
       List<DailyToDo> dailyEntityList = dailyToDoRepository.findAllByMember(id);
       List<DailyToDoDto> dtoList = new ArrayList<>();

        for (DailyToDo daily : dailyEntityList) {
            if(daily.getCheckList() == CheckList.UNDO) {
                dtoList.add(dailyEntityToDto(daily));
            }
        }

        return dtoList;
    }

    //삭제
    public void dailyDelete(DailyToDoDto dto){

        Long deleteEntityId = dailyToDoRepository.findByContent(dto.getContent(),
                dto.getStartTime(),dto.getStartMin()).getId();

        dailyToDoRepository.deleteById(deleteEntityId);
    }

    // Undo -> do
    public void dailyUndoToDo(DailyToDoDto dto){
       DailyToDo dailyEntity = dailyToDoRepository.findByContent(dto.getContent(),
               dto.getStartTime(), dto.getStartMin());

       dailyEntity.changeCheckListUndoToDo();

       dailyToDoRepository.save(dailyEntity);
    }

    @Scheduled(cron = "0 0 6 * * ?",zone = "Asia/Seoul")
    public void initializationDailyStatus(){
        List<DailyToDo> changeStatusList = dailyToDoRepository.findByCheckList(CheckList.DO);

        for (DailyToDo daily : changeStatusList) {

            daily.changeCheckListDoToUndo();

            dailyToDoRepository.save(daily);
        }
    }


    private DailyToDo dailyDtoToEntity(DailyToDoDto dto, Member member){

        DailyToDo dailyToDo = DailyToDo
                .builder()
                .content(dto.getContent())
                .workCategory(WorkCategory.Daily)
                .checkList(CheckList.UNDO)
                .startTime(dto.getStartTime())
                .startMin(dto.getStartMin())
                .endTime(dto.getEndTime())
                .endMin(dto.getEndMin())
                .member(member)
                .build();

        return dailyToDo;
    }

    private DailyToDoDto dailyEntityToDto(DailyToDo daily){

        DailyToDoDto dto = DailyToDoDto
                .builder()
                .content(daily.getContent())
                .checkList(daily.getCheckList().name())
                .workCategory(daily.getWorkCategory().name())
                .startTime(daily.getStartTime())
                .startMin(daily.getStartMin())
                .endTime(daily.getEndTime())
                .endMin(daily.getEndMin())
                .build();

        return dto;
    }

}
