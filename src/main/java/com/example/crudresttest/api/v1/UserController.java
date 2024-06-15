package com.example.crudresttest.api.v1;

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

    @GetMapping
    public List<User> getList() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @GetMapping("/by-ids")
    public List<User> getMany(@RequestParam List<UUID> ids) {
        return userRepository.findAllById(ids);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/bulk")
    public List<User> createMany(@RequestBody List<User> users) {
        return userRepository.saveAll(users);
    }

    @PatchMapping("/{id}")
    public User patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(user).readValue(patchNode);

        return userRepository.save(user);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<User> users = userRepository.findAllById(ids);

        for (User user : users) {
            objectMapper.readerForUpdating(user).readValue(patchNode);
            System.out.println(user);
        }

        List<User> resultUsers = userRepository.saveAll(users);
        System.out.println(resultUsers);
        return resultUsers.stream()
                .map(User::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        userRepository.deleteAllById(ids);
    }
}

