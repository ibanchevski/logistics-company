package com.student.logisticscompany.repository;

import com.student.logisticscompany.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findEmployeeEntitiesByUsername(String username);

    List<EmployeeEntity> findByType(String type);
}
