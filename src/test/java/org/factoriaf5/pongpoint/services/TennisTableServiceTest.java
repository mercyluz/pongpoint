package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.TennisTable;
import org.factoriaf5.pongpoint.repositories.TennisTableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TennisTableServiceTest {

    @Mock
    private TennisTableRepository tennisTableRepository;

    @InjectMocks
    private TennisTableService tennisTableService;

    private TennisTable tennisTable1;
    private TennisTable tennisTable2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        tennisTable1 = new TennisTable("Table 1");
        tennisTable2 = new TennisTable("Table 2");
    }

    @Test
    public void testCreateTennisTable() {
        // Configuración
        when(tennisTableRepository.save(tennisTable1)).thenReturn(tennisTable1);

        // Prueba
        TennisTable result = tennisTableService.createTennisTable(tennisTable1);

        // Verificación
        assertNotNull(result);
        assertEquals("Table 1", result.getName());
        verify(tennisTableRepository, times(1)).save(tennisTable1);
    }

    @Test
    public void testGetAllTennisTables() {
        // Configuración
        when(tennisTableRepository.findAll()).thenReturn(Arrays.asList(tennisTable1, tennisTable2));

        // Prueba
        List<TennisTable> result = tennisTableService.getAllTennisTables();

        // Verificación
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(tennisTableRepository, times(1)).findAll();
    }

    @Test
    public void testGetTennisTableById() {
        // Configuración
        when(tennisTableRepository.findById(1L)).thenReturn(Optional.of(tennisTable1));

        // Prueba
        TennisTable result = tennisTableService.getTennisTableById(1L);

        // Verificación
        assertNotNull(result);
        assertEquals("Table 1", result.getName());
        verify(tennisTableRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTennisTableById_NotFound() {
        // Configuración
        when(tennisTableRepository.findById(1L)).thenReturn(Optional.empty());

        // Prueba
        TennisTable result = tennisTableService.getTennisTableById(1L);

        // Verificación
        assertNull(result);
        verify(tennisTableRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteTennisTable() {
        // Configuración
        when(tennisTableRepository.existsById(1L)).thenReturn(true);

        // Prueba
        boolean result = tennisTableService.deleteTennisTable(1L);

        // Verificación
        assertTrue(result);
        verify(tennisTableRepository, times(1)).existsById(1L);
        verify(tennisTableRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteTennisTable_NotFound() {
        // Configuración
        when(tennisTableRepository.existsById(1L)).thenReturn(false);

        // Prueba
        boolean result = tennisTableService.deleteTennisTable(1L);

        // Verificación
        assertFalse(result);
        verify(tennisTableRepository, times(1)).existsById(1L);
    }

    @Test
    public void testUpdateTennisTable() {
        // Configuración
        when(tennisTableRepository.existsById(1L)).thenReturn(true);
        when(tennisTableRepository.save(tennisTable1)).thenReturn(tennisTable1);

        tennisTable1.setName("Updated Table");

        // Prueba
        TennisTable result = tennisTableService.updateTennisTable(1L, tennisTable1);

        // Verificación
        assertNotNull(result);
        assertEquals("Updated Table", result.getName());
        verify(tennisTableRepository, times(1)).existsById(1L);
        verify(tennisTableRepository, times(1)).save(tennisTable1);
    }

    @Test
    public void testUpdateTennisTable_NotFound() {
        // Configuración
        when(tennisTableRepository.existsById(1L)).thenReturn(false);

        // Prueba
        TennisTable result = tennisTableService.updateTennisTable(1L, tennisTable1);

        // Verificación
        assertNull(result);
        verify(tennisTableRepository, times(1)).existsById(1L);
    }
}
