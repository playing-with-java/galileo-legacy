package com.example.galileo_legacy.feature.user;

import com.example.galileo_legacy.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserRepository repository;
    private UserService service;

    @Before
    public void setUp() {
        repository = mock(UserRepository.class);
        service = new UserService(repository);
    }

    @Test
    public void shouldListAllUsers() {
        User user = User.builder().id(1L).username("jdoe").build();
        when(repository.findAll()).thenReturn(Arrays.asList(user));

        List<User> result = service.listAll();

        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    public void shouldCreateUserAndResetId() {
        User user = User.builder().id(10L).username("jdoe").build();
        when(repository.save(user)).thenReturn(user);

        User result = service.create(user);

        assertNull(user.getId());
        assertEquals(user, result);
        verify(repository).save(user);
    }

    @Test
    public void shouldUpdateExistingUser() {
        User existing = User.builder().id(1L)
                .username("old")
                .email("old@example.com")
                .firstName("Old")
                .lastName("User")
                .build();
        User input = User.builder().username("new").email("new@example.com").firstName("New").lastName("User").build();

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        User result = service.update(1L, input);

        assertEquals("new", existing.getUsername());
        assertEquals("new@example.com", existing.getEmail());
        assertEquals("New", existing.getFirstName());
        assertEquals("User", existing.getLastName());
        assertEquals(existing, result);
        verify(repository).save(existing);
    }

    @Test
    public void shouldThrowWhenUserDoesNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        try {
            service.getById(99L);
            fail("Expected ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertEquals("User not found with id 99", ex.getMessage());
        }
    }
}
