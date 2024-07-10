package com.example.crudresttest.api.v3;

import com.example.crudresttest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ToDo: Amplicode Designer -> Request Handling -> Get List
 *       DTO class: UserDto
 *       List filter: UserDtoFilter
 *       Pagination: ON
 *
 * ToDo: Amplicode Designer -> Request Handling -> Get One
 *       DTO class: UserDto
 *       Resource path: /{id}
 *
 * ToDo: Amplicode Designer -> Request Handling -> Get Many
 *       DTO class: UserDto
 *       Resource path: /by-ids
 *
 * ToDo: Amplicode Designer -> Request Handling -> Create
 *       DTO class: UserDto
 *
 * ToDo: Amplicode Designer -> Request Handling -> CreateMany
 *       DTO class: UserDto
 *       Resource path: /bulk
 *
 * ToDo: Amplicode Designer -> Request Handling -> Path
 *       DTO class: UserDto
 *       Resource path: /{id}
 *
 * ToDo: Amplicode Designer -> Request Handling -> PathMany
 *       DTO class: UserDto
 *
 * ToDo: Amplicode Designer -> Request Handling -> Update
 *       DTO class: UserDto
 *       Proxy service: None
 *       Resource path: /{id}
 *
 */

@RestController
@RequestMapping("/rest/v3")
public class UserController3 {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ObjectMapper objectMapper;

    public UserController3(UserRepository userRepository,
                           UserMapper userMapper,
                           ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.objectMapper = objectMapper;
    }


}

