package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.AddEmployeeDTO;
import com.student.logisticscompany.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserEntity registerNewUser(UserEntity user);
    UserEntity addNewEmployee(AddEmployeeDTO employeeDTO);

    List<UserEntity> searchUsers(String searchTerm);

    UserEntity findByUsername(String username);
}
