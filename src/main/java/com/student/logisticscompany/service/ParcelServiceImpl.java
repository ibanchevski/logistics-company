package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.entity.ParcelEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ParcelServiceImpl implements ParcelService {
    private final ModelMapper modelMapper;
    public ParcelEntity addNew(CreateParcelDTO createParcelDTO) {
        ParcelEntity parcel = modelMapper.map(createParcelDTO, ParcelEntity.class);
        System.out.println(parcel.getSender());
        System.out.println(parcel.getSender().getUsername());
        System.out.println(parcel.getSender().getId());
        System.out.println(parcel.getDescription());
        return new ParcelEntity();
    }
}
