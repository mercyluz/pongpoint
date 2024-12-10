package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;  // El servicio que estamos probando

    @Mock
    private ClientRepository clientRepository;  // El repositorio que es simulado

    private Client client;

    @BeforeEach
    public void setUp() {
        // Inicializar el objeto de cliente y mockear las dependencias
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setId(1L);
        client.setName("Test Client");
        client.setEmail("test@example.com");
    }

    @Test
    public void testGetClientById_WhenClientExists() {
        // Configurar el comportamiento del mock
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // Llamar al método del servicio
        Client foundClient = clientService.getClientById(1L);

        // Verificar el resultado
        assertNotNull(foundClient);
        assertEquals(client.getId(), foundClient.getId());
        assertEquals(client.getName(), foundClient.getName());

        // Verificar que se haya llamado una vez al método findById del repositorio
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetClientById_WhenClientDoesNotExist() {
        // Configurar el comportamiento del mock para cuando no se encuentra el cliente
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Llamar al método del servicio
        Client foundClient = clientService.getClientById(1L);

        // Verificar que no se haya encontrado el cliente
        assertNull(foundClient);

        // Verificar que se haya llamado una vez al método findById del repositorio
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateClient() {
        // Configurar el comportamiento del mock
        when(clientRepository.save(client)).thenReturn(client);

        // Llamar al método del servicio
        Client createdClient = clientService.createClient(client);

        // Verificar el resultado
        assertNotNull(createdClient);
        assertEquals(client.getName(), createdClient.getName());

        // Verificar que se haya llamado una vez al método save del repositorio
        verify(clientRepository, times(1)).save(client);
    }
}
