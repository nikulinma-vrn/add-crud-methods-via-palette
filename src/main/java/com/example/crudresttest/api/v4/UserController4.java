package com.example.crudresttest.api.v4;

import com.example.crudresttest.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ToDo: Amplicode Designer -> Request Handling -> Get List
 *       DTO class: UserDto
 *       Pagination: ON
 *       List filter: UserFilter
 *       Proxy service: UserServiceImpl
 *
 * ToDo: Amplicode Designer -> Request Handling -> Get One
 *       DTO class: UserDto
 *       Resource path: /{id}
 *       Proxy service: UserServiceImpl
 *
 * ToDo: Amplicode Designer -> Request Handling -> Get Many
 *       DTO class: UserDto
 *       Resource path: /by-ids
 *       Proxy service: UserServiceImpl
 *
 * ToDo: Amplicode Designer -> Request Handling -> Create
 *       DTO class: UserDto
 *       Proxy service: UserServiceImpl
 *
 * ToDo: Amplicode Designer -> Request Handling -> CreateMany
 *       DTO class: UserDto
 *       Resource path: /bulk
 *       Proxy service: UserServiceImpl
 *
 * ToDo: Amplicode Designer -> Request Handling -> Path
 *       DTO class: UserDto
 *       Resource path: /{id}
 *       Proxy service: UserServiceImpl
 *
 * ToDo: Amplicode Designer -> Request Handling -> PathMany
 *       DTO class: UserDto
 *       Proxy service: UserServiceImpl
 *
 * ToDo: Amplicode Designer -> Request Handling -> Delete
 *       DTO class: UserDto
 *       Resource path: /{id}
 *       Proxy service: UserServiceImpl
 *
 * ToDo: Amplicode Designer -> Request Handling -> DeleteMany
 *       Proxy service: UserServiceImpl
 *
 */

@RestController
@RequestMapping("/rest/v4")
public class UserController4 {

    private final UserService userService;

    public UserController4(UserService userService) {
        this.userService = userService;
    }

}

