package com.student.logisticscompany.repository;

import com.student.logisticscompany.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findEmployeeEntitiesByUsername(String username);
}
