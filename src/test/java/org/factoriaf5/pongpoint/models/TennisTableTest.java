package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TennisTableTest {

    private TennisTable tennisTable;

    @BeforeEach
    public void setUp() {
        
        tennisTable = new TennisTable("Table 1");
    }

    @Test
    public void testTennisTableConstructor() {
       
        assertNotNull(tennisTable);
        assertEquals("Table 1", tennisTable.getName());
    }

    @Test
    public void testSetName() {
       
        tennisTable.setName("New Table");
        assertEquals("New Table", tennisTable.getName());
    }

    @Test
    public void testGetId() {
      
        assertNull(tennisTable.getId());
    }
}
