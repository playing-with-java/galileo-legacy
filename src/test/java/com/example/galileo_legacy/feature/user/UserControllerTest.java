package com.example.galileo_legacy.feature.user;

import com.example.galileo_legacy.feature.user.dto.UserRequest;
import com.example.galileo_legacy.feature.user.dto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserService service;
    private UserMapper mapper;
    private UserController controller;

    @BeforeEach
    public void setUp() {
        service = mock(UserService.class);
        mapper = mock(UserMapper.class);
        controller = new UserController(service, mapper);
    }

    @Test
    public void shouldReturnAllUsers() {
        User user = User.builder().id(1L).username("jdoe").build();
        UserResponse response = UserResponse.builder().id(1L).username("jdoe").build();

        when(service.listAll()).thenReturn(Collections.singletonList(user));
        when(mapper.toResponse(user)).thenReturn(response);

        ResponseEntity<List<UserResponse>> result = controller.getAllUsers();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Collections.singletonList(response), result.getBody());
        verify(service).listAll();
        verify(mapper).toResponse(user);
    }

    @Test
    public void shouldCreateUser() {
        UserRequest request = UserRequest.builder().username("jdoe").email("jdoe@example.com").build();
        User created = User.builder().id(2L).username("jdoe").email("jdoe@example.com").build();
        UserResponse response = UserResponse.builder().id(2L).username("jdoe").email("jdoe@example.com").build();

        when(mapper.toEntity(request)).thenReturn(created);
        when(service.create(created)).thenReturn(created);
        when(mapper.toResponse(created)).thenReturn(response);

        ResponseEntity<UserResponse> result = controller.createUser(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(service).create(created);
    }
}
