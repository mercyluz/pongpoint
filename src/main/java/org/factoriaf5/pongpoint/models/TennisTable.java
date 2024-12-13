package org.factoriaf5.pongpoint.models;


import jakarta.persistence.*;


@Entity
@Table(name = "tables")  
public class TennisTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

   
    public TennisTable() {}

    public TennisTable(String name) {
        this.name = name;
    }

   
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
