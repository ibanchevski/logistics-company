package com.student.logisticscompany.dto;

import com.student.logisticscompany.entity.OfficeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmployeeDTO {
    private String username;
    private String name;
    private String password;
    private String officeName;
    private EmployeeType type;
}
