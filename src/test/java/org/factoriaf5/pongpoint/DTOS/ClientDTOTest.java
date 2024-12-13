package org.factoriaf5.pongpoint.DTOS;

import org.factoriaf5.pongpoint.models.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientDTOTest {

    @Test
    void testClientDTOConstructorWithParameters() {
        // Given
        Long expectedId = 1L;
        String expectedName = "John Doe";
        String expectedEmail = "john.doe@example.com";

        // When
        ClientDTO clientDTO = new ClientDTO(expectedId, expectedName, expectedEmail);

        // Then
        assertEquals(expectedId, clientDTO.getId());
        assertEquals(expectedName, clientDTO.getName());
        assertEquals(expectedEmail, clientDTO.getEmail());
    }

    @Test
    void testClientDTOConstructorFromClient() {
        // Given
        Client client = new Client("Jane Doe", "jane.doe@example.com");
        client.setId(2L);

        // When
        ClientDTO clientDTO = new ClientDTO(client);

        // Then
        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getName(), clientDTO.getName());
        assertEquals(client.getEmail(), clientDTO.getEmail());
    }

    @Test
    void testSettersAndGetters() {
        // Given
        ClientDTO clientDTO = new ClientDTO();

        // When
        clientDTO.setId(3L);
        clientDTO.setName("Alice");
        clientDTO.setEmail("alice@example.com");

        // Then
        assertEquals(3L, clientDTO.getId());
        assertEquals("Alice", clientDTO.getName());
        assertEquals("alice@example.com", clientDTO.getEmail());
    }
}
