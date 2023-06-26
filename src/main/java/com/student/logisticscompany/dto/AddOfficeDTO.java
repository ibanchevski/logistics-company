package com.student.logisticscompany.dto;

import com.student.logisticscompany.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AddOfficeDTO {
    private String name;

    @NotBlank
    private String address;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateAdded = LocalDate.now();

    @NotNull
    private UserEntity addedBy;
}
