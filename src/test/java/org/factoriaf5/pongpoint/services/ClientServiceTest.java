package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client("John Doe", "john.doe@example.com");
    }

    @Test
    void testCreateClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client createdClient = clientService.createClient(client);

        assertNotNull(createdClient);
        assertEquals("John Doe", createdClient.getName());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testGetClientById() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client foundClient = clientService.getClientById(1L);

        assertNotNull(foundClient);
        assertEquals("John Doe", foundClient.getName());
    }

    @Test
    void testGetClientByIdNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Client foundClient = clientService.getClientById(1L);

        assertNull(foundClient);
    }

    @Test
    void testUpdateClient() {
        Client updatedClient = new Client("Jane Doe", "jane.doe@example.com");
        updatedClient.setId(1L);
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(clientRepository.save(updatedClient)).thenReturn(updatedClient);

        Client result = clientService.updateClient(1L, updatedClient);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(clientRepository, times(1)).save(updatedClient);
    }

    @Test
    void testUpdateClientNotFound() {
        Client updatedClient = new Client("Jane Doe", "jane.doe@example.com");
        when(clientRepository.existsById(1L)).thenReturn(false);

        Client result = clientService.updateClient(1L, updatedClient);

        assertNull(result);
    }

    @Test
    void testDeleteClient() {
        when(clientRepository.existsById(1L)).thenReturn(true);

        boolean result = clientService.deleteClient(1L);

        assertTrue(result);
        verify(clientRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteClientNotFound() {
        when(clientRepository.existsById(1L)).thenReturn(false);

        boolean result = clientService.deleteClient(1L);

        assertFalse(result);
    }
}
