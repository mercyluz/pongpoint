package org.factoriaf5.pongpoint.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

public class ClientControllerTest {

    @Mock
    private ClientService clientService; // Mock del servicio

    @InjectMocks
    private ClientController clientController; // Controlador a probar

    private MockMvc mockMvc;
    private Client client;

    @BeforeEach
    public void setUp() {
        // Inicializa MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        // Crea un cliente para las pruebas
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
    }

    @Test
    public void testCreateClient_Success() throws Exception {
        when(clientService.createClient(Mockito.any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testCreateClient_Failure_InvalidId() throws Exception {
        // Configura la excepción en el servicio
        when(clientService.createClient(Mockito.any(Client.class)))
                .thenThrow(IllegalAccessException.class);

        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(Arrays.asList(client));

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    public void testGetClientById_Success() throws Exception {
        when(clientService.getClientById(1L)).thenReturn(client);

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetClientById_NotFound() throws Exception {
        when(clientService.getClientById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateClient_Success() throws Exception {
        Client updatedClient = new Client();
        updatedClient.setName("Jane Doe");

        when(clientService.updateClient(Mockito.eq(1L), Mockito.any(Client.class)))
                .thenReturn(updatedClient);

        mockMvc.perform(put("/api/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    public void testUpdateClient_NotFound() throws Exception {
        Client updatedClient = new Client();
        updatedClient.setName("Jane Doe");

        when(clientService.updateClient(Mockito.eq(1L), Mockito.any(Client.class)))
                .thenReturn(null);

        mockMvc.perform(put("/api/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedClient)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteClient_Success() throws Exception {
        when(clientService.deleteClient(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/clients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteClient_NotFound() throws Exception {
        when(clientService.deleteClient(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/clients/1"))
                .andExpect(status().isNotFound());
    }
}
