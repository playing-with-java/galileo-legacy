package com.example.galileo_legacy.feature.user;

import com.example.galileo_legacy.feature.user.dto.UserRequest;
import com.example.galileo_legacy.feature.user.dto.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Api(tags = "Users", description = "Operations for managing users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation(value = "List all users", notes = "Returns all available users", response = UserResponse.class,
            responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users returned successfully")
    })
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = service.listAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a user by id", notes = "Finds a user by its identifier", response = UserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<UserResponse> getUser(@ApiParam(value = "User identifier", required = true)
                                                @PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.getById(id)));
    }

    @PostMapping
    @ApiOperation(value = "Create a user", notes = "Creates a new user", response = UserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created successfully")
    })
    public ResponseEntity<UserResponse> createUser(@ApiParam(value = "User payload", required = true)
                                                   @RequestBody UserRequest request) {
        User created = service.create(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a user", notes = "Updates an existing user", response = UserResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<UserResponse> updateUser(@ApiParam(value = "User identifier", required = true)
                                                    @PathVariable Long id,
                                                    @ApiParam(value = "User payload", required = true)
                                                    @RequestBody UserRequest request) {
        User updated = service.update(id, mapper.toEntity(request));
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user", notes = "Removes a user by its identifier")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "User identifier", required = true)
                                           @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
