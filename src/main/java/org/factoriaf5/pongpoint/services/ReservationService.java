package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.DTOS.ReservationDTO;
import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.models.Reservation;
import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.models.TennisTable;
import org.factoriaf5.pongpoint.repositories.ClientRepository;
import org.factoriaf5.pongpoint.repositories.ReservationRepository;
import org.factoriaf5.pongpoint.repositories.TennisTableRepository;
import org.factoriaf5.pongpoint.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private TennisTableRepository tableRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;

    // Crear una nueva reserva
    public Reservation createReservation(ReservationDTO reservationRequest) {
        Client client = clientRepository.findById(reservationRequest.getClientId()).orElse(null);
        TennisTable table = tableRepository.findById(reservationRequest.getTableId()).orElse(null);
        Schedule schedule = scheduleRepository.findById(reservationRequest.getScheduleId()).orElse(null);
        
        if (client == null || table == null || schedule == null) {
            return null; // Si no se encuentran el cliente, la mesa o el horario
        }

        // Calcular el precio dependiendo del día de la semana
        Double price = reservationRequest.getPrice();
        if (isWeekend(schedule.getStartDateTime())) {
            price += price * 0.30; // Incrementar el precio en un 30% si es fin de semana
        }

        Reservation reservation = new Reservation();
        return reservationRepository.save(reservation);
    }

    // Verificar si la fecha es un fin de semana (sábado o domingo)
    private boolean isWeekend(LocalDateTime dateTime) {
        DayOfWeek day = dateTime.getDayOfWeek();
        return (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
    }

    // Cancelar una reserva
    public boolean cancelReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime reservationTime = reservation.getSchedule().getStartDateTime();

            // Si la reserva es dentro de 24 horas, no se puede cancelar
            if (reservationTime.minusHours(24).isAfter(now)) {
                reservationRepository.delete(reservation);
                return true;
            }
        }
        return false;
    }

    public List<Reservation> getAllReservations() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllReservations'");
    }

    public Reservation getReservationById(Long reservationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservationById'");
    }

    public void deleteReservation(Long reservationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReservation'");
    }

    public Reservation createReservation(Long userId, Long tableSelectionId, Long scheduleId, LocalDateTime date,
            String status, Double price) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReservation'");
    }

    public Reservation updateReservation(Long reservationId, Reservation updatedReservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReservation'");
    }
}
