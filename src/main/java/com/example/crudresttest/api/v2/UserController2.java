package com.example.crudresttest.api.v2;

import com.example.crudresttest.entity.User;
import com.example.crudresttest.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ToDo: Amplicode Designer -> Request Handling -> Get List
 *       DTO class: User
 *       Filter: UserFilter
 *       Proxy service: None
 *       Pagination: ON
 */

@RestController
@RequestMapping("/rest/v2")
public class UserController2 {

    private final UserRepository userRepository;

    public UserController2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Page<User> getList(@ModelAttribute UserFilter filter, Pageable pageable) {
        Specification<User> spec = filter.toSpecification();
        return userRepository.findAll(spec, pageable);
    }
}

