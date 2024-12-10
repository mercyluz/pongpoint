package org.factoriaf5.pongpoint.models;


import jakarta.persistence.*;


@Entity
@Table(name = "tables")  // Especificamos el nombre de la tabla en la base de datos
public class TennisTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Constructor vacío
    public TennisTable() {}

    // Constructor con parámetros
    public TennisTable(String name) {
        this.name = name;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
