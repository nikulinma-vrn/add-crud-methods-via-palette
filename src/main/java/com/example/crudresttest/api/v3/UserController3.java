package com.example.crudresttest.api.v3;

import com.example.crudresttest.dto.UserDto;
import com.example.crudresttest.entity.User;
import com.example.crudresttest.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/rest/v3")
public class UserController3 {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserController3(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping
    public Page<UserDto> getList(@ModelAttribute UserDtoFilter filter, Pageable pageable) {
        Specification<User> spec = filter.toSpecification();
        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(userMapper::toDto);
    }
//todo methods with DTO without proxy-service

}

