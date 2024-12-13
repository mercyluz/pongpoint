package org.factoriaf5.pongpoint.DTOS;

import org.factoriaf5.pongpoint.models.Client;

public class ClientDTO {

    private Long id;
    private String name;
    private String email;

    // Constructor vacío
    public ClientDTO() {}

    // Constructor con parámetros
    public ClientDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Constructor a partir de un Client
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
