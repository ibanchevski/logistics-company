package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.AddEmployeeDTO;
import com.student.logisticscompany.dto.EmployeeType;
import com.student.logisticscompany.entity.RoleEntity;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService  {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity registerNewUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public UserEntity addNewEmployee(AddEmployeeDTO addEmployeeDTO) {
        UserEntity newUser = modelMapper.map(addEmployeeDTO, UserEntity.class);
        RoleEntity employeeRole = new RoleEntity();

        if (addEmployeeDTO.getType().equals(EmployeeType.OFFICE)) {
            employeeRole.setAuthority("EMPLOYEE");
        } else {
            employeeRole.setAuthority("DELIVERY");
        }

        newUser.setAuthorities(Set.of(employeeRole));
        newUser.setEnabled(false);

        return this.userRepository.save(newUser);
    }

    @Override
    public List<UserEntity> searchUsers(String searchTerm) {
        // Postgresql wildcard in like statement %searchTerm%
        return this.userRepository.searchBySearchTerm(String.format("%%%s%%", searchTerm));
    }
}
