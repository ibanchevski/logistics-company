package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.entity.ParcelEntity;
import org.springframework.stereotype.Service;

public interface ParcelService {
    ParcelEntity addNew(CreateParcelDTO createParcelDTO);
}
