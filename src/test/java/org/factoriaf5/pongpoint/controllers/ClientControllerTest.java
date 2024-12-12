package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Client;
import org.factoriaf5.pongpoint.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client("John Doe", "john.doe@example.com");
    }

    @Test
    void testCreateClient() throws Exception {
        when(clientService.createClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(clientService, times(1)).createClient(any(Client.class));
    }

    @Test
    void testGetAllClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(List.of(client));

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));

        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void testGetClientById() throws Exception {
        when(clientService.getClientById(1L)).thenReturn(client);

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    void testGetClientByIdNotFound() throws Exception {
        when(clientService.getClientById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isNotFound());

        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    void testUpdateClient() throws Exception {
        Client updatedClient = new Client("Jane Doe", "jane.doe@example.com");
        updatedClient.setId(1L);
        when(clientService.updateClient(eq(1L), any(Client.class))).thenReturn(updatedClient);

        mockMvc.perform(put("/api/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane Doe\", \"email\":\"jane.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));

        verify(clientService, times(1)).updateClient(eq(1L), any(Client.class));
    }

    @Test
    void testDeleteClient() throws Exception {
        when(clientService.deleteClient(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/clients/1"))
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).deleteClient(1L);
    }

    @Test
    void testDeleteClientNotFound() throws Exception {
        when(clientService.deleteClient(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/clients/1"))
                .andExpect(status().isNotFound());

        verify(clientService, times(1)).deleteClient(1L);
    }
}
