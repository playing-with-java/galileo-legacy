package com.example.galileo_legacy.feature.user;

import com.example.galileo_legacy.feature.user.dto.UserRequest;
import com.example.galileo_legacy.feature.user.dto.UserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    public void shouldMapUserToResponse() {
        User user = User.builder()
                .id(1L)
                .username("jdoe")
                .email("jdoe@example.com")
                .firstName("Jane")
                .lastName("Doe")
                .build();

        UserResponse response = mapper.toResponse(user);

        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getUsername(), response.getUsername());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getFirstName(), response.getFirstName());
        assertEquals(user.getLastName(), response.getLastName());
    }

    @Test
    public void shouldMapRequestToEntity() {
        UserRequest request = UserRequest.builder()
                .username("jdoe")
                .email("jdoe@example.com")
                .firstName("Jane")
                .lastName("Doe")
                .build();

        User entity = mapper.toEntity(request);

        assertNotNull(entity);
        assertEquals(request.getUsername(), entity.getUsername());
        assertEquals(request.getEmail(), entity.getEmail());
        assertEquals(request.getFirstName(), entity.getFirstName());
        assertEquals(request.getLastName(), entity.getLastName());
    }

    @Test
    public void shouldReturnNullWhenUserIsNull() {
        assertNull(mapper.toResponse(null));
    }
}
