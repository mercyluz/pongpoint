package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.TableSelection;
import org.factoriaf5.pongpoint.repositories.TableSelectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TableSelectionServiceTest {

    @Autowired
    private TableSelectionService tableSelectionService;

    @MockBean
    private TableSelectionRepository tableSelectionRepository;

    private TableSelection tableSelection;

    @BeforeEach
    public void setUp() {
        tableSelection = new TableSelection(null, "Room 1", 10.0);
    }

    @Test
    public void testCreateTableSelection() {
        Mockito.when(tableSelectionRepository.save(Mockito.any(TableSelection.class))).thenReturn(tableSelection);

        TableSelection createdTableSelection = tableSelectionService.createTableSelection("Room 1", 10.0);
        
        assertNotNull(createdTableSelection);
        assertEquals("Room 1", createdTableSelection.getLocation());
        assertEquals(10.0, createdTableSelection.getBasePrice());
    }

    @Test
    public void testGetTableSelectionById() {
        Mockito.when(tableSelectionRepository.findById(1L)).thenReturn(java.util.Optional.of(tableSelection));

        TableSelection foundTableSelection = tableSelectionService.getTableSelectionById(1L);
        
        assertNotNull(foundTableSelection);
        assertEquals("Room 1", foundTableSelection.getLocation());
    }
}

