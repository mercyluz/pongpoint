package org.factoriaf5.pongpoint.services;


import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Obtener un cliente por ID
    public Client getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null); // Devuelve null si no se encuentra
    }

    // Crear un nuevo cliente
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
}
