package com.student.logisticscompany.service;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.entity.OfficeEntity;
import com.student.logisticscompany.entity.ParcelEntity;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ParcelServiceImpl implements ParcelService {
    private final ModelMapper modelMapper;
    private final ParcelRepository parcelRepository;
    private final CompanyService companyService;
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
    public List<ParcelEntity> getAllDeliveredInPeriod(LocalDate startDate, LocalDate endDate) {
        return parcelRepository.findByDeliveryDateBetween(startDate, endDate);
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

            companyService.addRevenue(parcel.get().getFee());
        }
    }

    @Override
    public void cancel(Long id) {
        Optional<ParcelEntity> parcel = parcelRepository.findById(id);

        if (parcel.isPresent()) {
            parcelRepository.delete(parcel.get());

            // We assume each cancelled parcel as company loss
            companyService.addRevenue(parcel.get().getFee() * -1);
        }
    }
}
