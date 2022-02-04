package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.work.DailyToDo;
import com.oldaim.routineproject.entity.work.WeeklyToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyToDoRepository extends JpaRepository<DailyToDo, Long> {
}
