package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.entity.ParcelEntity;
import com.student.logisticscompany.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ParcelService {
    ParcelEntity addNew(CreateParcelDTO createParcelDTO);

    List<ParcelEntity> getAll();


    long getCount();

    void deliver(Long id, UserEntity deliveryEmployee);
    void cancel(Long id);
}
