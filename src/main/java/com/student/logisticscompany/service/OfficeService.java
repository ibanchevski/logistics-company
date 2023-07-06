package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.AddOfficeDTO;
import com.student.logisticscompany.entity.OfficeEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface OfficeService {
    OfficeEntity create(@Valid AddOfficeDTO addOfficeDTO);
    List<OfficeEntity> getAll();

    void delete(Long officeId);

    long getCount();
}
