package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    private Schedule schedule;

    // Este método se ejecuta antes de cada prueba para inicializar el objeto Schedule
    @BeforeEach
    public void setUp() {
        // Inicializamos el objeto Schedule con parámetros válidos
        schedule = new Schedule(1L, LocalTime.of(10, 0), LocalTime.of(12, 0), LocalDate.of(2024, 12, 1));
    }

    // Test para el constructor vacío (sin parámetros)
    @Test
    public void testConstructorEmpty() {
        // Usamos el constructor vacío
        Schedule emptySchedule = new Schedule(0L, null);

        // Verificamos que los valores del objeto creado sean los esperados (nulos o por defecto)
        assertNull(emptySchedule.getStartTime(), "La hora de inicio debe ser nula");
        assertNull(emptySchedule.getEndTime(), "La hora de finalización debe ser nula");
        assertNull(emptySchedule.getDate(), "La fecha debe ser nula");
    }

    // Test para el constructor con parámetros
    @Test
    public void testConstructorWithParameters() {
        // Verificamos que los valores se asignen correctamente
        assertEquals(1L, schedule.getScheduleId(), "El ID del horario no es correcto");
        assertEquals(LocalTime.of(10, 0), schedule.getStartTime(), "La hora de inicio no es correcta");
        assertEquals(LocalTime.of(12, 0), schedule.getEndTime(), "La hora de finalización no es correcta");
        assertEquals(LocalDate.of(2024, 12, 1), schedule.getDate(), "La fecha no es correcta");
    }

    // Test para los métodos Setters y Getters
    @Test
    public void testSettersAndGetters() {
        // Usamos los setters para cambiar los valores
        schedule.setScheduleId(2L);
        schedule.setStartTime(LocalTime.of(14, 0));
        schedule.setEndTime(LocalTime.of(16, 0));
        schedule.setDate(LocalDate.of(2024, 12, 2));

        // Verificamos que los valores se hayan actualizado correctamente
        assertEquals(2L, schedule.getScheduleId(), "El ID del horario no se actualizó correctamente");
        assertEquals(LocalTime.of(14, 0), schedule.getStartTime(), "La hora de inicio no se actualizó correctamente");
        assertEquals(LocalTime.of(16, 0), schedule.getEndTime(), "La hora de finalización no se actualizó correctamente");
        assertEquals(LocalDate.of(2024, 12, 2), schedule.getDate(), "La fecha no se actualizó correctamente");
    }

    // Test para el método toString
    @Test
    public void testToString() {
        // Verificamos que el método toString devuelve el formato correcto
        String expected = "Schedule [scheduleId=1, startTime=10:00, endTime=12:00, date=2024-12-01]";
        assertEquals(expected, schedule.toString(), "El método toString no devuelve el formato esperado");
    }
}
