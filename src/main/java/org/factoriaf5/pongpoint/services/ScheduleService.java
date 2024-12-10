package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // Obtener horarios disponibles para una mesa
    public List<Schedule> getSchedulesByTable(Long tableId) {
        return scheduleRepository.findByTableId(tableId);
    }

    // Obtener un horario específico por su ID
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    // Actualizar la disponibilidad de un horario
    public Schedule updateScheduleAvailability(Long scheduleId, Boolean available) {
        Schedule schedule = getScheduleById(scheduleId);
        if (schedule != null) {
            schedule.setAvailable(available);
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    public Schedule createOrUpdateSchedule(Schedule schedule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrUpdateSchedule'");
    }

    public boolean deleteSchedule(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSchedule'");
    }

    public List<Schedule> getAllSchedules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllSchedules'");
    }

    public Schedule createSchedule(Schedule schedule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSchedule'");
    }
}
