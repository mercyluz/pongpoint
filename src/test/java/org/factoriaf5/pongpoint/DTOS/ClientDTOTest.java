package org.factoriaf5.pongpoint.DTOS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientDTOTest {

    private ClientDTO clientDTO;

    
    @BeforeEach
    public void setUp() {
        
        clientDTO = new ClientDTO(1L, "John Doe", "johndoe@example.com");
    }

   
    public void testGettersAndSetters() {
       
        assertThat(clientDTO.getId()).isEqualTo(1L);
        assertThat(clientDTO.getName()).isEqualTo("John Doe");
        assertThat(clientDTO.getEmail()).isEqualTo("johndoe@example.com");

       
        clientDTO.setId(2L);
        clientDTO.setName("Jane Smith");
        clientDTO.setEmail("janesmith@example.com");

        
        assertThat(clientDTO.getId()).isEqualTo(2L);
        assertThat(clientDTO.getName()).isEqualTo("Jane Smith");
        assertThat(clientDTO.getEmail()).isEqualTo("janesmith@example.com");
    }

    
    @Test
    public void testConstructor() {
       
        ClientDTO newClientDTO = new ClientDTO(3L, "Alice Johnson", "alice@example.com");
        assertThat(newClientDTO.getId()).isEqualTo(3L);
        assertThat(newClientDTO.getName()).isEqualTo("Alice Johnson");
        assertThat(newClientDTO.getEmail()).isEqualTo("alice@example.com");
    }

    
    @Test
    public void testEmptyConstructor() {
        ClientDTO emptyClientDTO = new ClientDTO();
       
        assertThat(emptyClientDTO.getId()).isNull();
        assertThat(emptyClientDTO.getName()).isNull();
        assertThat(emptyClientDTO.getEmail()).isNull();
    }
}

