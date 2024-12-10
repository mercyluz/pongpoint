package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // Crear una nueva instancia de User antes de cada prueba
        user = new User();
        user.setUserId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setRole("Admin");
    }

    @Test
    void testUserGettersAndSetters() {
        // Verificar que los valores iniciales se asignan correctamente
        assertEquals(1L, user.getUserId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("Admin", user.getRole());

        // Cambiar valores mediante los setters
        user.setUserId(2L);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setRole("User");

        // Verificar que los valores se hayan actualizado correctamente
        assertEquals(2L, user.getUserId());
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("User", user.getRole());
    }

    @Test
    void testUserConstructor() {
        // Verificar que los valores del constructor se asignan correctamente
        User newUser = new User();
        newUser.setUserId(3L);
        newUser.setName("Mark Smith");
        newUser.setEmail("mark.smith@example.com");
        newUser.setRole("Manager");

        // Verificar que la instancia tiene los valores correctos
        assertEquals(3L, newUser.getUserId());
        assertEquals("Mark Smith", newUser.getName());
        assertEquals("mark.smith@example.com", newUser.getEmail());
        assertEquals("Manager", newUser.getRole());
    }
}
