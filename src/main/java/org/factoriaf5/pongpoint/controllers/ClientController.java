package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Obtener un cliente por ID
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    // Actualizar un cliente por ID
    
    // Actualizar un cliente
    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(
            @PathVariable Long clientId, @RequestBody Client updatedClient) {
        Client client = clientService.updateClient(clientId, updatedClient);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Cliente no encontrado
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    // Eliminar un cliente por ID
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        boolean isDeleted = clientService.deleteClient(clientId);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
