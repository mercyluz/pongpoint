package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Crear un nuevo cliente
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Obtener todos los clientes
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Obtener un cliente por ID
    public Client getClientById(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        return client.orElse(null);
    }

    // Actualizar un cliente
    public Client updateClient(Long clientId, Client updatedClient) {
        if (!clientRepository.existsById(clientId)) {
            return null; // Si el cliente no existe
        }
        updatedClient.setId(clientId); // Asegurarse de mantener el ID correcto
        return clientRepository.save(updatedClient);
    }

    // Eliminar un cliente
    public boolean deleteClient(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            return false; // El cliente no existe
        }
        clientRepository.deleteById(clientId);
        return true;
    }
}
