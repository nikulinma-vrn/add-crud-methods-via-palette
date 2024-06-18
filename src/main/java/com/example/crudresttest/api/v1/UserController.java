package com.example.crudresttest.api.v1;

import com.example.crudresttest.api.v3.UserMapper;
import com.example.crudresttest.dto.UserDto;
import com.example.crudresttest.entity.User;
import com.example.crudresttest.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Insert in this controller methods with default parameters
 */

@RestController
@RequestMapping("/rest/v1")
public class UserController {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;


    public UserController(UserRepository userRepository,
                          ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    /*
     * ToDo: Amplicode Designer -> Request Handling -> Get List
     *       DTO class: User
     *       Pagination: OFF
     *
     * ToDo: Amplicode Designer -> Request Handling -> Get One
     *       DTO class: User
     *       Resource path: /{id}
     *
     * ToDo: Amplicode Designer -> Request Handling -> Get Many
     *       DTO class: User
     *       Resource path: /by-ids
     *
     * ToDo: Amplicode Designer -> Request Handling -> Create
     *       DTO class: User
     *
     * ToDo: Amplicode Designer -> Request Handling -> CreateMany
     *       DTO class: User
     *       Resource path: /bulk
     *
     * ToDo: Amplicode Designer -> Request Handling -> Path
     *       DTO class: User
     *       Resource path: /{id}
     *
     * ToDo: Amplicode Designer -> Request Handling -> PathMany
     *       DTO class: User
     *
     * ToDo: Amplicode Designer -> Request Handling -> Delete
     *       DTO class: User
     *       Resource path: /{id}
     *
     * ToDo: Amplicode Designer -> Request Handling -> DeleteMany
     *       DTO class: User
     *
     */


}