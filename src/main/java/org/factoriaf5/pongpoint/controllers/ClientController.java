package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Obtener todos los clientes
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);  // Retorna null si no se encuentra el cliente
    }

    // Crear un cliente
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
