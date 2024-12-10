package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Obtener todos los horarios disponibles
    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        if (schedules.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(schedules);
    }

    // Obtener los horarios disponibles para una mesa específica
    @GetMapping("/table/{tableId}")
    public ResponseEntity<List<Schedule>> getSchedulesByTable(@PathVariable Long tableId) {
        List<Schedule> schedules = scheduleService.getSchedulesByTable(tableId);
        if (schedules.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(schedules);
    }

    // Obtener un horario específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        if (schedule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schedule);
    }


    // Crear un nuevo horario
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule newSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.status(201).body(newSchedule);
    }

    // Actualizar la disponibilidad de un horario
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateScheduleAvailability(@PathVariable Long id, @RequestBody Boolean available) {
        Schedule updatedSchedule = scheduleService.updateScheduleAvailability(id, available);
        if (updatedSchedule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSchedule);
    }

    // Eliminar un horario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        boolean deleted = scheduleService.deleteSchedule(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
