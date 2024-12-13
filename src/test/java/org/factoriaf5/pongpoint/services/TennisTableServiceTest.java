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
   
        when(tennisTableRepository.save(tennisTable1)).thenReturn(tennisTable1);

        
        TennisTable result = tennisTableService.createTennisTable(tennisTable1);

        assertNotNull(result);
        assertEquals("Table 1", result.getName());
        verify(tennisTableRepository, times(1)).save(tennisTable1);
    }

    @Test
    public void testGetAllTennisTables() {
        
        when(tennisTableRepository.findAll()).thenReturn(Arrays.asList(tennisTable1, tennisTable2));

       
        List<TennisTable> result = tennisTableService.getAllTennisTables();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(tennisTableRepository, times(1)).findAll();
    }

    @Test
    public void testGetTennisTableById() {
       
        when(tennisTableRepository.findById(1L)).thenReturn(Optional.of(tennisTable1));

        
        TennisTable result = tennisTableService.getTennisTableById(1L);

 
        assertNotNull(result);
        assertEquals("Table 1", result.getName());
        verify(tennisTableRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTennisTableById_NotFound() {
        
        when(tennisTableRepository.findById(1L)).thenReturn(Optional.empty());

        TennisTable result = tennisTableService.getTennisTableById(1L);

        
        assertNull(result);
        verify(tennisTableRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteTennisTable() {
      
        when(tennisTableRepository.existsById(1L)).thenReturn(true);

       
        boolean result = tennisTableService.deleteTennisTable(1L);

      
        assertTrue(result);
        verify(tennisTableRepository, times(1)).existsById(1L);
        verify(tennisTableRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteTennisTable_NotFound() {
     
        when(tennisTableRepository.existsById(1L)).thenReturn(false);

     
        boolean result = tennisTableService.deleteTennisTable(1L);

        
        assertFalse(result);
        verify(tennisTableRepository, times(1)).existsById(1L);
    }

    @Test
    public void testUpdateTennisTable() {
      
        when(tennisTableRepository.existsById(1L)).thenReturn(true);
        when(tennisTableRepository.save(tennisTable1)).thenReturn(tennisTable1);

        tennisTable1.setName("Updated Table");

     
        TennisTable result = tennisTableService.updateTennisTable(1L, tennisTable1);

      
        assertNotNull(result);
        assertEquals("Updated Table", result.getName());
        verify(tennisTableRepository, times(1)).existsById(1L);
        verify(tennisTableRepository, times(1)).save(tennisTable1);
    }

    @Test
    public void testUpdateTennisTable_NotFound() {
       
        when(tennisTableRepository.existsById(1L)).thenReturn(false);

       
        TennisTable result = tennisTableService.updateTennisTable(1L, tennisTable1);

      
        assertNull(result);
        verify(tennisTableRepository, times(1)).existsById(1L);
    }
}
