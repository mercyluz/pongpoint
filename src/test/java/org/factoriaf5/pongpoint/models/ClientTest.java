package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    private Client client;

    @BeforeEach
    public void setUp() {
       
        client = new Client("John Doe", "johndoe@example.com");
    }

    @Test
    public void testClientConstructor() {
        assertNotNull(client);
        assertEquals("John Doe", client.getName());
        assertEquals("johndoe@example.com", client.getEmail());
    }

    @Test
    public void testSetName() {
        client.setName("Jane Doe");
        assertEquals("Jane Doe", client.getName());
    }

    @Test
    public void testSetEmail() {
        client.setEmail("janedoe@example.com");
        assertEquals("janedoe@example.com", client.getEmail());
    }

    @Test
    public void testReservations() {
        Reservation reservation1 = Mockito.mock(Reservation.class);
        Reservation reservation2 = Mockito.mock(Reservation.class);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        client.setReservations(reservations);

        assertNotNull(client.getReservations());
        assertEquals(2, client.getReservations().size());
    }
}
