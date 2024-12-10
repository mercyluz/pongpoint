package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableSelectionTest {

    @Test
    void testEqualsAndHashCode() {
        TableSelection table1 = new TableSelection(1L, "Room 1", 10.0);
        TableSelection table2 = new TableSelection(1L, "Room 1", 10.0);
        TableSelection table3 = new TableSelection(2L, "Room 2", 15.0);

        // Prueba de igualdad (equals)
        assertEquals(table1, table2);  // Deben ser iguales
        assertNotEquals(table1, table3);  // Deben ser diferentes

        // Prueba de hashCode
        assertEquals(table1.hashCode(), table2.hashCode());  // Deben tener el mismo hashCode
        assertNotEquals(table1.hashCode(), table3.hashCode());  // Deben tener diferentes hashCode
    }

    @Test
    void testToString() {
        TableSelection table = new TableSelection(1L, "Room 1", 10.0);

        // Verifica que la salida del método toString sea correcta
        assertEquals("TableSelection{tableId=1, location='Room 1', basePrice=10.0}", table.toString());
    }
}
