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
    public void testDeleteClient_NotFound() throws Exception {
        when(clientService.deleteClient(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/clients/1"))
                .andExpect(status().isNotFound());
    }
}
