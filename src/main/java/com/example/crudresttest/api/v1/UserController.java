package com.example.crudresttest.api.v1;

import com.example.crudresttest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}