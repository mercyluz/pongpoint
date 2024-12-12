package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class) // Esta anotación inicializa los mocks
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository; // Mock del repositorio

    @InjectMocks
    private ClientService clientService; // Inyecta el servicio con el repositorio simulado

    private Client client;

    @BeforeEach
    public void setUp() {
        // Configura un cliente para las pruebas
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
    }

    @Test
    public void testCreateClient_Success() throws IllegalAccessException {
        // Simula el comportamiento de save() del repositorio
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.createClient(client);

        assertNotNull(result); // Asegúrate de que el cliente no sea nulo
        assertEquals("John Doe", result.getName()); // Verifica que el nombre sea el esperado
        verify(clientRepository, times(1)).save(client); // Verifica que save() fue llamado una vez
    }

    @Test
    public void testCreateClient_Failure_InvalidId() {
        // Configura un cliente con un ID preasignado
        client.setId(1L);

        // Verifica que se lanza la excepción si el cliente tiene un ID
        assertThrows(IllegalAccessException.class, () -> clientService.createClient(client));

        // Verifica que el repositorio no fue llamado
        verify(clientRepository, never()).save(any());
    }

    @Test
    public void testGetAllClients() {
        // Simula la respuesta del repositorio
        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> result = clientService.getAllClients();

        assertNotNull(result);
        assertEquals(1, result.size()); // Verifica que solo hay un cliente en la lista
        assertEquals("John Doe", result.get(0).getName()); // Verifica el nombre del cliente
    }

    @Test
    public void testGetClientById_Success() {
        // Simula la respuesta del repositorio para el cliente con ID 1
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(1L);

        assertNotNull(result); // Verifica que el cliente no sea nulo
        assertEquals(1L, result.getId()); // Verifica que el ID sea correcto
    }

    @Test
    public void testGetClientById_NotFound() {
        // Simula que no se encuentra el cliente con ID 1
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Client result = clientService.getClientById(1L);

        assertNull(result); // Verifica que el cliente sea nulo
    }

    @Test
    public void testUpdateClient_Success() {
        Client updatedClient = new Client();
        updatedClient.setName("Jane Doe");

        // Simula que el cliente existe en el repositorio
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(clientRepository.save(updatedClient)).thenReturn(updatedClient);

        Client result = clientService.updateClient(1L, updatedClient);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName()); // Verifica que el nombre se haya actualizado
    }

    @Test
    public void testUpdateClient_NotFound() {
        Client updatedClient = new Client();
        updatedClient.setName("Jane Doe");

        // Simula que el cliente no existe
        when(clientRepository.existsById(1L)).thenReturn(false);

        Client result = clientService.updateClient(1L, updatedClient);

        assertNull(result); // Verifica que el cliente no fue actualizado
    }

    @Test
    public void testDeleteClient_Success() {
        // Simula que el cliente existe
        when(clientRepository.existsById(1L)).thenReturn(true);

        boolean result = clientService.deleteClient(1L);

        assertTrue(result); // Verifica que la eliminación fue exitosa
        verify(clientRepository, times(1)).deleteById(1L); // Verifica que el repositorio haya sido llamado para eliminar el cliente
    }

    @Test
    public void testDeleteClient_NotFound() {
        // Simula que el cliente no existe
        when(clientRepository.existsById(1L)).thenReturn(false);

        boolean result = clientService.deleteClient(1L);

        assertFalse(result); // Verifica que la eliminación falló
        verify(clientRepository, never()).deleteById(1L); // Verifica que el repositorio no fue llamado
    }
}
