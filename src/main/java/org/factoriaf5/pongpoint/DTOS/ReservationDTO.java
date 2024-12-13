package org.factoriaf5.pongpoint.DTOS;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDTO {

    
    private Long clientId;
    private Long tableId;
    private Long scheduleId;
    private Double price;

  
    public ReservationDTO() {}

   
    public ReservationDTO(Long clientId, Long tableId, Long scheduleId, Double price) {
        this.clientId = clientId;
        this.tableId = tableId;
        this.scheduleId = scheduleId;
        this.price = price;
    }


    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
