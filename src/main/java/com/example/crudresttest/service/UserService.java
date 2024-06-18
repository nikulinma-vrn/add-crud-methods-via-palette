package com.example.crudresttest.service;

import com.example.crudresttest.api.v2.UserFilter;
import com.example.crudresttest.api.v3.UserDtoFilter;
import com.example.crudresttest.dto.UserDto;
import com.example.crudresttest.entity.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserService {
    Page<UserDto> getList(UserDtoFilter filter, Pageable pageable);

    UserDto getOne(UUID id);

    List<UserDto> getMany(List<UUID> ids);

    UserDto create(UserDto dto);

    List<UserDto> createMany(List<UserDto> dtos);

    UserDto patch(UUID id, JsonNode patchNode) throws IOException;

    List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException;

    UserDto delete(UUID id);

    void deleteMany(List<UUID> ids);
}
