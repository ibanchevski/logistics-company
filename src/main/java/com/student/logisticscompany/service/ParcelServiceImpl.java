package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.entity.OfficeEntity;
import com.student.logisticscompany.entity.ParcelEntity;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ParcelServiceImpl implements ParcelService {
    private final ModelMapper modelMapper;
    private final ParcelRepository parcelRepository;
    private final UserService userService;

    @Override
    public ParcelEntity addNew(CreateParcelDTO createParcelDTO) {
        ParcelEntity parcel = modelMapper.map(createParcelDTO, ParcelEntity.class);
        return parcelRepository.save(parcel);
    }

    @Override
    public List<ParcelEntity> getAll() {
        return parcelRepository.findAll();
    }

    @Override
    public long getCount() {
        return parcelRepository.count();
    }

    @Override
    public void deliver(Long id, UserEntity deliveryEmployee) {
        Optional<ParcelEntity> parcel = parcelRepository.findById(id);

        if (parcel.isPresent()) {
            parcel.get().setDelivered(true);
            parcel.get().setDeliveryDate(LocalDate.now());
            parcel.get().setDeliveredBy(deliveryEmployee);

            parcelRepository.save(parcel.get());
        }
    }

    @Override
    public void cancel(Long id) {
        parcelRepository.deleteById(id);
    }
}
