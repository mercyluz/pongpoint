package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.DTOS.ClientDTO;
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

   
    public Client createClient(Client client) throws IllegalAccessException {
        
        if (client.getId() != null && !String.valueOf(client.getId()).isEmpty()) {
            throw new IllegalAccessException("Id cannot be auto assigned");
        }
        return clientRepository.save(client);
    }

  
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

   
    public Client getClientById(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        return client.orElse(null);
    }

   
    public Client updateClient(Long clientId, Client updatedClient) {
        if (!clientRepository.existsById(clientId)) {
            return null; 
        }
        updatedClient.setId(clientId); 
        return clientRepository.save(updatedClient);
    }

    
    public boolean deleteClient(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            return false; 
        }
        clientRepository.deleteById(clientId);
        return true;
    }

    public ClientDTO convertToDTO(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToDTO'");
    }
}
