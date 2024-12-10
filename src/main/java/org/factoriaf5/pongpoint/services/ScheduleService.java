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

    // Crear un nuevo schedule
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // Obtener todos los schedules
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // Obtener un schedule por ID
    public Schedule getScheduleById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.orElse(null);
    }

    // Eliminar un schedule
    public boolean deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar un schedule
    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        if (scheduleRepository.existsById(id)) {
            updatedSchedule.setId(id);
            return scheduleRepository.save(updatedSchedule);
        }
        return null;
    }
}
