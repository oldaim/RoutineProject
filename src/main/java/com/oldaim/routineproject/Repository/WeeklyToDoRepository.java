package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.work.WeeklyToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyToDoRepository extends JpaRepository<WeeklyToDo, Long> {
}
