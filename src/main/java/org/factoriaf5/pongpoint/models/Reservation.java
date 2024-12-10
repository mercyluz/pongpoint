package org.factoriaf5.pongpoint.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")  // Especificamos el nombre de la tabla en la base de datos
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TennisTable table;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private Double price;
    private Boolean confirmed;

    // Constructor vacío
    public Reservation() {}

    // Constructor con parámetros
    public Reservation(Client client, TennisTable table2, Schedule schedule, Double price) {
        this.client = client;
        this.table = table2;
        this.schedule = schedule;
        this.price = price;
        this.confirmed = true;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TennisTable getTable() {
        return table;
    }

    public void setTable(TennisTable table) {
        this.table = table;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
