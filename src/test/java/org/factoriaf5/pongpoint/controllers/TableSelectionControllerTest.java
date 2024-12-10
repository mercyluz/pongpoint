package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.TableSelection;
import org.factoriaf5.pongpoint.services.TableSelectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class TableSelectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TableSelectionService tableSelectionService;

    private TableSelection tableSelection;

    @BeforeEach
    public void setUp() {
        tableSelection = new TableSelection(1L, "Room 1", 10.0);
    }

    @Test
    public void testCreateTableSelection() throws Exception {
        Mockito.when(tableSelectionService.createTableSelection(Mockito.anyString(), Mockito.anyDouble())).thenReturn(tableSelection);

        mockMvc.perform(post("/api/tableselections")
                .param("location", "Room 1")
                .param("basePrice", "10.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.location").value("Room 1"))
                .andExpect(jsonPath("$.basePrice").value(10.0));
    }

    @Test
    public void testGetTableSelectionById() throws Exception {
        Mockito.when(tableSelectionService.getTableSelectionById(1L)).thenReturn(tableSelection);

        mockMvc.perform(get("/api/tableselections/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("Room 1"))
                .andExpect(jsonPath("$.basePrice").value(10.0));
    }
}
