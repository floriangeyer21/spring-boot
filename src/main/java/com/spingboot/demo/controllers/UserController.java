package com.spingboot.demo.controllers;

import com.spingboot.demo.domain.dto.UserResponseDto;
import com.spingboot.demo.service.interfaces.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final String PAGE_SIZE = "1000";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/most-active")
    public List<UserResponseDto> getMostActive(
            @RequestParam(defaultValue = PAGE_SIZE) int quantity) {
        return userService.findMostActive(quantity);
    }
}
