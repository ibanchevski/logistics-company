package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.AddOfficeDTO;
import com.student.logisticscompany.entity.OfficeEntity;
import com.student.logisticscompany.repository.OfficeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class OfficeServiceImpl implements OfficeService {
    private final ModelMapper modelMapper;
    private final OfficeRepository officeRepository;

    @Override
    public OfficeEntity create(@Valid AddOfficeDTO addOfficeDTO) {
        return officeRepository.save(modelMapper.map(addOfficeDTO, OfficeEntity.class));
    }

    @Override
    public List<OfficeEntity> getAll() {
        return officeRepository.findAll()
                .stream()
                .toList();
    }

    @Override
    public void delete(Long officeId) {
        officeRepository.deleteById(officeId);
    }
}
