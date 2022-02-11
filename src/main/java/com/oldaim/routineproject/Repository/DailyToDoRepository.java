package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.work.DailyToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyToDoRepository extends JpaRepository<DailyToDo, Long> {
    @Query("SELECT d FROM DailyToDo d WHERE d.content = ?1" +
            " AND d.startTime =?2 AND d.startMin =?3")
    DailyToDo findByContent(String content, Integer startTime, Integer startMin);

    @Query("SELECT d FROM  DailyToDo d WHERE d.member.id =?1")
    List<DailyToDo> findAllByMember(Long id);
}
