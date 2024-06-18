package com.example.crudresttest.api.v3;

import com.example.crudresttest.dto.UserDto;
import com.example.crudresttest.entity.User;
import com.example.crudresttest.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 * ToDo: Amplicode Designer -> Request Handling -> Get List
 *       DTO class: UserDto
 *       List filter: UserDtoFilter
 *       Proxy service: None
 *       Pagination: ON
 *
 * ToDo: Amplicode Designer -> Request Handling -> Get One
 *       DTO class: UserDto
 *       Proxy service: None
 *       Resource path: /{id}
 *
 * ToDo: Amplicode Designer -> Request Handling -> Get Many
 *       DTO class: UserDto
 *       Proxy service: None
 *       Resource path: /by-ids
 *
 * ToDo: Amplicode Designer -> Request Handling -> Create
 *       DTO class: UserDto
 *       Proxy service: None
 *
 * ToDo: Amplicode Designer -> Request Handling -> CreateMany
 *       DTO class: UserDto
 *       Proxy service: None
 *       Resource path: /bulk
 *
 * ToDo: Amplicode Designer -> Request Handling -> Path
 *       DTO class: UserDto
 *       Proxy service: None
 *       Resource path: /{id}
 *
 * ToDo: Amplicode Designer -> Request Handling -> PathMany
 *       DTO class: UserDto
 *       Proxy service: None
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

    @GetMapping
    public Page<UserDto> getList(@ModelAttribute UserDtoFilter filter, Pageable pageable) {
        Specification<User> spec = filter.toSpecification();
        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(userMapper::toDto);
    }

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userMapper.toDto(userOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<UserDto> getMany(@RequestParam List<UUID> ids) {
        List<User> users = userRepository.findAllById(ids);
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User resultUser = userRepository.save(user);
        return userMapper.toDto(resultUser);
    }

    @PostMapping("/bulk")
    public List<UserDto> createMany(@RequestBody List<UserDto> dtos) {
        Collection<User> users = dtos.stream()
                .map(userMapper::toEntity)
                .toList();
        List<User> resultUsers = userRepository.saveAll(users);
        return resultUsers.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PatchMapping("/{id}")
    public UserDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        UserDto userDto = userMapper.toDto(user);
        objectMapper.readerForUpdating(userDto).readValue(patchNode);
        userMapper.updateWithNull(userDto, user);

        User resultUser = userRepository.save(user);
        return userMapper.toDto(resultUser);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<User> users = userRepository.findAllById(ids);

        for (User user : users) {
            UserDto userDto = userMapper.toDto(user);
            objectMapper.readerForUpdating(userDto).readValue(patchNode);
            userMapper.updateWithNull(userDto, user);
        }

        List<User> resultUsers = userRepository.saveAll(users);
        return resultUsers.stream()
                .map(User::getId)
                .toList();
    }
}

