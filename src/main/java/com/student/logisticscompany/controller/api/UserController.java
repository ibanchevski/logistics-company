package com.student.logisticscompany.controller.api;

import com.student.logisticscompany.dto.UserDTO;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<UserDTO> getUsers(@RequestParam String searchTerm) {
        return userService.searchUsers(searchTerm)
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }
}
