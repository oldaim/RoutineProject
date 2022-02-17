package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.work.CheckList;
import com.oldaim.routineproject.entity.work.WeeklyToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeeklyToDoRepository extends JpaRepository<WeeklyToDo, Long> {
    @Query("SELECT w FROM WeeklyToDo w WHERE w.content = ?1 and w.startTime = ?2" +
            " and w.startMin = ?3 and w.day = ?4")
    WeeklyToDo findByContent(String content,Integer startTime,Integer startMin,String day);

    @Query("SELECT w FROM WeeklyToDo w WHERE w.member.id = ?1")
    List<WeeklyToDo> findAllByMember(Long id);

    @Query("SELECT w FROM WeeklyToDo w WHERE w.checkList = ?1")
    List<WeeklyToDo> findByStatus(CheckList aDo);
}
