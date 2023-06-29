package com.student.logisticscompany.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String username;
    private String type;
    private UserDTO addedBy;
    private OfficeDTO office;
    private LocalDate dateAdded;
    private boolean isActivated;
}
