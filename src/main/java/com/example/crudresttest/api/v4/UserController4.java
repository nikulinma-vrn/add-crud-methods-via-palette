package com.example.crudresttest.api.v4;

import com.example.crudresttest.api.v3.UserDtoFilter;
import com.example.crudresttest.dto.UserDto;
import com.example.crudresttest.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/*
 * ToDo: Amplicode Designer -> Request Handling -> Get List
 *       DTO class: UserDto
 *       Pagination: ON
 *       List filter: UserDtoFilter
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

    @GetMapping
    public Page<UserDto> getList(@ModelAttribute UserDtoFilter filter, Pageable pageable) {
        return userService.getList(filter, pageable);
    }

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable UUID id) {
        return userService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<UserDto> getMany(@RequestParam List<UUID> ids) {
        return userService.getMany(ids);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return userService.create(dto);
    }

    @PostMapping("/bulk")
    public List<UserDto> createMany(@RequestBody List<UserDto> dtos) {
        return userService.createMany(dtos);
    }

    @PatchMapping("/{id}")
    public UserDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return userService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return userService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable UUID id) {
        return userService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        userService.deleteMany(ids);
    }
}

