package com.example.crudresttest.service;

import com.example.crudresttest.api.v2.UserFilter;
import com.example.crudresttest.api.v3.UserDtoFilter;
import com.example.crudresttest.api.v3.UserMapper;
import com.example.crudresttest.dto.UserDto;
import com.example.crudresttest.entity.User;
import com.example.crudresttest.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public Page<UserDto> getList(UserDtoFilter filter, Pageable pageable) {
        Specification<User> spec = filter.toSpecification();
        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(userMapper::toDto);
    }

    @Override
    public UserDto getOne(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userMapper.toDto(userOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @Override
    public List<UserDto> getMany(List<UUID> ids) {
        List<User> users = userRepository.findAllById(ids);
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = userMapper.toEntity(dto);
        User resultUser = userRepository.save(user);
        return userMapper.toDto(resultUser);
    }

    @Override
    public List<UserDto> createMany(List<UserDto> dtos) {
        Collection<User> users = dtos.stream()
                .map(userMapper::toEntity)
                .toList();
        List<User> resultUsers = userRepository.saveAll(users);
        return resultUsers.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto patch(UUID id, JsonNode patchNode) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        UserDto userDto = userMapper.toDto(user);
        objectMapper.readerForUpdating(userDto).readValue(patchNode);
        userMapper.updateWithNull(userDto, user);

        User resultUser = userRepository.save(user);
        return userMapper.toDto(resultUser);
    }

    @Override
    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
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

    @Override
    public UserDto delete(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return userMapper.toDto(user);
    }

    @Override
    public void deleteMany(List<UUID> ids) {
        userRepository.deleteAllById(ids);
    }
}
