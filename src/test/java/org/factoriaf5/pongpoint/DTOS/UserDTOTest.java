package org.factoriaf5.pongpoint.DTOS;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {

    @Test
    void testConstructorAndGettersSetters() {
        // Arrange
        Long userId = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String role = "Admin";

        // Act
        UserDTO userDTO = new UserDTO(userId, name, email, role);

        // Assert
        assertEquals(userId, userDTO.getUserId());
        assertEquals(name, userDTO.getName());
        assertEquals(email, userDTO.getEmail());
        assertEquals(role, userDTO.getRole());
    }

    @Test
    void testSetters() {
        // Arrange
        UserDTO userDTO = new UserDTO(1L, "Initial Name", "initial@example.com", "User");

        // Act
        userDTO.setUserId(2L);
        userDTO.setName("Updated Name");
        userDTO.setEmail("updated@example.com");
        userDTO.setRole("Admin");

        // Assert
        assertEquals(2L, userDTO.getUserId());
        assertEquals("Updated Name", userDTO.getName());
        assertEquals("updated@example.com", userDTO.getEmail());
        assertEquals("Admin", userDTO.getRole());
    }

    @Test
    void testEmptyConstructor() {
        // Arrange
        UserDTO userDTO = new UserDTO(null, null, null, null);

        // Act & Assert
        assertNull(userDTO.getUserId());
        assertNull(userDTO.getName());
        assertNull(userDTO.getEmail());
        assertNull(userDTO.getRole());
    }

    @Test
    void testDefaultValues() {
        // Arrange
        UserDTO userDTO = new UserDTO(1L, "Alice", "alice@example.com", "Manager");

        // Act
        Long userId = userDTO.getUserId();
        String name = userDTO.getName();
        String email = userDTO.getEmail();
        String role = userDTO.getRole();

        // Assert
        assertNotNull(userId);
        assertNotNull(name);
        assertNotNull(email);
        assertNotNull(role);
    }
}
