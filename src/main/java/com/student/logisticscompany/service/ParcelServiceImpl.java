package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.entity.ParcelEntity;
import com.student.logisticscompany.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ParcelServiceImpl implements ParcelService {
    private final ModelMapper modelMapper;
    private final ParcelRepository parcelRepository;
    private final UserService userService;

    @Override
    public ParcelEntity addNew(CreateParcelDTO createParcelDTO) {
        ParcelEntity parcel = modelMapper.map(createParcelDTO, ParcelEntity.class);
        parcel.setSender(userService.findByUsername(createParcelDTO.getSenderUsername()));
        parcel.setReceiver(userService.findByUsername(createParcelDTO.getReceiverUsername()));

        if (!createParcelDTO.isOffice()) {
            parcel.setAddress(createParcelDTO.getAddress());
        }
        return parcelRepository.save(parcel);
    }

    @Override
    public List<ParcelEntity> getAll() {
        return parcelRepository.findAll();
    }
}
