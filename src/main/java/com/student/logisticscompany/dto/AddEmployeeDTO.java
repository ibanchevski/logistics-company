package com.student.logisticscompany.dto;

import com.student.logisticscompany.entity.OfficeEntity;
import com.student.logisticscompany.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AddEmployeeDTO {
    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    @NotNull
    private EmployeeType type;

    @NotNull
    private OfficeEntity office;

    private UserEntity addedBy;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateAdded = LocalDate.now();

    private boolean activated = false;
}
