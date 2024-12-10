package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

public class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientRepository clientRepository; // Simulación del repositorio

    @InjectMocks
    private ClientController clientController; // El controlador que estamos probando

    private Client client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        
        // Crear un cliente para las pruebas
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
    }

    @Test
    public void testGetAllClients() throws Exception {
        // Simular la respuesta del repositorio
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));

        // Realizar la solicitud GET
        MvcResult result = mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andReturn();

        // Verificar que el repositorio fue llamado
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testGetClientById_Found() throws Exception {
        // Simular la respuesta del repositorio
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // Realizar la solicitud GET por ID
        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        // Verificar que el repositorio fue llamado
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetClientById_NotFound() throws Exception {
        // Simular que no se encuentra un cliente con ese ID
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Realizar la solicitud GET por ID
        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isNotFound());

        // Verificar que el repositorio fue llamado
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateClient() throws Exception {
        // Simular que el repositorio guarda el cliente
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        // Realizar la solicitud POST
        mockMvc.perform(post("/api/clients")
                .contentType("application/json")
                .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        // Verificar que el repositorio fue llamado
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}
