package com.student.logisticscompany.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ActivateEmployeeDTO {
    @NotNull
    @NotEmpty
    private String username;
}
