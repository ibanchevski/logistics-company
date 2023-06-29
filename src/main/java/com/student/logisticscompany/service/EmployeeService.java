package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.ActivateEmployeeDTO;
import com.student.logisticscompany.dto.AddEmployeeDTO;
import com.student.logisticscompany.dto.RegisterEmployeeDTO;
import com.student.logisticscompany.entity.EmployeeEntity;
import com.student.logisticscompany.entity.OfficeEntity;
import com.student.logisticscompany.entity.UserEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity add(AddEmployeeDTO addEmployeeDTO);
    EmployeeEntity activate(ActivateEmployeeDTO activateEmployeeDTO) throws Exception;
    EmployeeEntity register(RegisterEmployeeDTO activateEmployeeDTO);

    OfficeEntity getOffice(String username);

    List<EmployeeEntity> getAll();
}
