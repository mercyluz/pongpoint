package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.DTOS.ClientDTO;
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

    
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Client createdClient = clientService.createClient(client);
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // En caso de error con el ID
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

   
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

   @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable Long clientId,
            @RequestBody Client updatedClient) {

        
        Client client = clientService.updateClient(clientId, updatedClient);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ClientDTO clientDTO = clientService.convertToDTO(client);

        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        boolean isDeleted = clientService.deleteClient(clientId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
}
