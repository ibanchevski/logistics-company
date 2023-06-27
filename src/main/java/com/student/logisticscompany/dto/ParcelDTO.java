package com.student.logisticscompany.dto;

import com.student.logisticscompany.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParcelDTO {
    private long id;
    private String description;
    private UserEntity sender;
    private UserEntity receiver;
    private String address;
    private OfficeDTO officeSend;
    private OfficeDTO officeReceive;
    private boolean isOffice;
    private boolean isDelivered;
}
