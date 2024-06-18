package com.example.crudresttest.api.v2;

import com.example.crudresttest.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ToDo: Amplicode Designer -> Request Handling -> Get List
 *       DTO class: User
 *       Filter: all field
 *       Pagination: ON
 */

@RestController
@RequestMapping("/rest/v2")
public class UserController2 {

    private final UserRepository userRepository;

    public UserController2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

