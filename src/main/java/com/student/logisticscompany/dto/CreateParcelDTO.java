package com.student.logisticscompany.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateParcelDTO {
    private String description;
    private String senderUsername;
    private String receiverUsername;
    private long officeSendId;
    private long officeReceiveId;
    private long employeeId;
    private String address;
    private Boolean isOffice;
    private double fee;
}
