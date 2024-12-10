package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TennisTableTest {

    private TennisTable tennisTable;

    @BeforeEach
    public void setUp() {
        // Inicializamos el objeto TennisTable antes de cada prueba
        tennisTable = new TennisTable("Table 1");
    }

    @Test
    public void testTennisTableConstructor() {
        // Verificamos que el constructor funciona correctamente
        assertNotNull(tennisTable);
        assertEquals("Table 1", tennisTable.getName());
    }

    @Test
    public void testSetName() {
        // Verificamos que el método setName funciona correctamente
        tennisTable.setName("New Table");
        assertEquals("New Table", tennisTable.getName());
    }

    @Test
    public void testGetId() {
        // El id debe ser null antes de que el objeto se guarde en la base de datos
        assertNull(tennisTable.getId());
    }
}
