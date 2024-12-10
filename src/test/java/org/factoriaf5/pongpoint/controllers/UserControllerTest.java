package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.User;
import org.factoriaf5.pongpoint.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User(1L, "John Doe", "john.doe@example.com", "user");  // Creamos un usuario de prueba
    }

    @Test
    void testCreateUser() throws Exception {
        // Simulamos que el servicio creará un usuario con los mismos datos
        when(userService.createUser("John Doe", "john.doe@example.com", "user")).thenReturn(user);

        // Hacemos una petición POST a /users
        mockMvc.perform(post("/users")
                .param("name", "John Doe")
                .param("email", "john.doe@example.com")
                .param("role", "user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Esperamos que la respuesta sea 200 OK
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.role").value("user"));

        // Verificamos que se haya llamado al servicio con los parámetros correctos
        verify(userService, times(1)).createUser("John Doe", "john.doe@example.com", "user");
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(user);

        // Simulamos que el servicio devolverá una lista de usuarios
        when(userService.getAllUsers()).thenReturn(users);

        // Hacemos una petición GET a /users
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Esperamos que la respuesta sea 200 OK
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].role").value("user"));

        // Verificamos que se haya llamado al servicio una vez
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() throws Exception {
        // Simulamos que el servicio devolverá el usuario cuando se busque por ID
        when(userService.getUserById(1L)).thenReturn(user);

        // Hacemos una petición GET a /users/{userId}
        mockMvc.perform(get("/users/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Esperamos que la respuesta sea 200 OK
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.role").value("user"));

        // Verificamos que se haya llamado al servicio con el ID correcto
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testUpdateUser() throws Exception {
        // Creamos un usuario actualizado
        User updatedUser = new User(1L, "John Updated", "john.updated@example.com", "admin");

        // Simulamos que el servicio actualizará el usuario correctamente
        when(userService.updateUser(1L, updatedUser)).thenReturn(updatedUser);

        // Hacemos una petición PUT a /users/{userId}
        mockMvc.perform(put("/users/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"John Updated\", \"email\": \"john.updated@example.com\", \"role\": \"admin\" }"))
                .andExpect(status().isOk())  // Esperamos que la respuesta sea 200 OK
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.email").value("john.updated@example.com"))
                .andExpect(jsonPath("$.role").value("admin"));

        // Verificamos que se haya llamado al servicio con los datos correctos
        verify(userService, times(1)).updateUser(1L, updatedUser);
    }

    @Test
    void testDeleteUser() throws Exception {
        // No es necesario simular nada para este caso

        // Hacemos una petición DELETE a /users/{userId}
        mockMvc.perform(delete("/users/{userId}", 1L))
                .andExpect(status().isNoContent());  // Esperamos que la respuesta sea 204 No Content

        // Verificamos que se haya llamado al servicio con el ID correcto
        verify(userService, times(1)).deleteUser(1L);
    }
}
