package com.student.logisticscompany.dto;

import com.student.logisticscompany.entity.OfficeEntity;
import com.student.logisticscompany.entity.UserEntity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateParcelDTO {
    private String description;

    @NotNull
    private UserEntity sender;

    @NotNull
    private UserEntity receiver;

    private OfficeEntity officeSend;

    @NotNull
    private OfficeEntity officeReceive;

    private UserEntity employee;

    private String address;

    private Boolean isOffice;

    @NotNull
    @DecimalMin("0.01")
    private double weight;

    private double fee;
}
