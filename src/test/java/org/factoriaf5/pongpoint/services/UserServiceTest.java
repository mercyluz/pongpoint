package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.User;
import org.factoriaf5.pongpoint.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Simulamos el repositorio de usuarios

    @InjectMocks
    private UserService userService;  // El servicio que estamos probando

    private User user;  // El objeto de usuario de prueba

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks antes de cada prueba
        user = new User(1L, "John Doe", "john.doe@example.com", "user");  // Creamos un usuario de prueba
    }

    @Test
    void testCreateUser() {
        // Simulamos el comportamiento de save() para que devuelva el mismo usuario
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Llamamos al método del servicio
        User createdUser = userService.createUser(user.getName(), user.getEmail(), user.getRole());

        // Verificamos el resultado
        assertEquals("John Doe", createdUser.getName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
        assertEquals("user", createdUser.getRole());

        // Verificamos que save() fue llamado una vez
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetAllUsers() {
        // Preparamos el comportamiento del mock para findAll()
        when(userRepository.findAll()).thenReturn(List.of(user));

        // Llamamos al método del servicio
        List<User> allUsers = userService.getAllUsers();

        // Verificamos que la lista de usuarios no esté vacía y contiene el usuario esperado
        assertFalse(allUsers.isEmpty());
        assertEquals(1, allUsers.size());
        assertEquals("John Doe", allUsers.get(0).getName());
    }

    @Test
    void testGetUserById() {
        // Preparamos el comportamiento del mock para findById()
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Llamamos al método del servicio
        User foundUser = userService.getUserById(1L);

        // Verificamos el resultado
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
    }

    @Test
    void testGetUserById_UserNotFound() {
        // Simulamos que no se encuentra el usuario
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Llamamos al método del servicio y verificamos que lance una excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User(1L, "John Updated", "john.updated@example.com", "admin");

        // Preparamos el comportamiento del mock para findById()
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        // Simulamos el comportamiento de save() para que devuelva el usuario actualizado
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Llamamos al método del servicio
        User result = userService.updateUser(1L, updatedUser);

        // Verificamos que el usuario fue actualizado correctamente
        assertEquals("John Updated", result.getName());
        assertEquals("john.updated@example.com", result.getEmail());
        assertEquals("admin", result.getRole());

        // Verificamos que save() fue llamado una vez
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        // Preparamos el comportamiento del mock para findById()
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Llamamos al método del servicio
        userService.deleteUser(1L);

        // Verificamos que deleteById() fue llamado una vez
        verify(userRepository, times(1)).deleteById(1L);
    }
}
