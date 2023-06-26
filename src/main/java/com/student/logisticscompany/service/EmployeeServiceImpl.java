package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.ActivateEmployeeDTO;
import com.student.logisticscompany.dto.AddEmployeeDTO;
import com.student.logisticscompany.dto.EmployeeType;
import com.student.logisticscompany.dto.RegisterEmployeeDTO;
import com.student.logisticscompany.entity.EmployeeEntity;
import com.student.logisticscompany.entity.OfficeEntity;
import com.student.logisticscompany.entity.RoleEntity;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public EmployeeEntity add(@Valid AddEmployeeDTO addEmployeeDTO) {
        return this.employeeRepository.save(modelMapper.map(addEmployeeDTO, EmployeeEntity.class));
    }

    @Override
    public OfficeEntity getOffice(String username) {
        return this.employeeRepository.findEmployeeEntitiesByUsername(username).getOffice();
    }

    @Override
    public EmployeeEntity activate(ActivateEmployeeDTO activateEmployeeDTO) throws Exception {
        EmployeeEntity employee = this.employeeRepository.findEmployeeEntitiesByUsername(activateEmployeeDTO.getUsername());

        if (employee == null) {
            throw new Exception("Username not found!");
        }

        return employee;
    }

    @Override
    @Transactional
    public EmployeeEntity register(RegisterEmployeeDTO registerEmployeeDTO) {
        EmployeeEntity employee = this.employeeRepository.findEmployeeEntitiesByUsername(registerEmployeeDTO.getUsername());
        employee.setUsername(registerEmployeeDTO.getUsername());
        employee.setName(registerEmployeeDTO.getName());
        employee.setActivated(true);

        this.employeeRepository.save(employee);

        UserEntity userEmployee = modelMapper.map(registerEmployeeDTO, UserEntity.class);

        // TODO: Fix employee roles
        RoleEntity userRole = new RoleEntity();
        String authority = employee.getType().equals("OFFICE") ? "EMPLOYEE" : "DELIVERY";
        userRole.setAuthority(authority);
        userEmployee.setAuthorities(Set.of(userRole));

        this.userService.registerNewUser(userEmployee);
        return employee;
    }
}
