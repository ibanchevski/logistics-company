package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.entity.ParcelEntity;
import com.student.logisticscompany.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface ParcelService {
    ParcelEntity addNew(CreateParcelDTO createParcelDTO);

    List<ParcelEntity> getAll();

    List<ParcelEntity> getAllDeliveredInPeriod(LocalDate startDate, LocalDate endDate);

    List<ParcelEntity> getAllByStatus(String status);


    long getCount();

    void deliver(Long id, UserEntity deliveryEmployee);
    void cancel(Long id);
}
