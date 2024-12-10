package org.factoriaf5.pongpoint.repositories;


import org.factoriaf5.pongpoint.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTableId(Long tableId);
}





