package com.student.logisticscompany.service;

import com.student.logisticscompany.entity.CompanyEntity;
import com.student.logisticscompany.entity.ParcelEntity;
import com.student.logisticscompany.repository.CompanyRepository;
import com.student.logisticscompany.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ParcelRepository parcelRepository;

    @Override
    public CompanyEntity getCompany() {
        // There is always only one company
        Optional<CompanyEntity> company = companyRepository.findById(1L);

        return company.get();
    }

    @Override
    public void addRevenue(double revenue) {
        // There is always only one company
        Optional<CompanyEntity> company = companyRepository.findById(1L);

        if (company.isPresent()) {
            double currentRevenue = company.get().getRevenue();
            currentRevenue += revenue;
            company.get().setRevenue(currentRevenue);

            companyRepository.save(company.get());
        }
    }

    @Override
    public double calculateRveneuForPriod(LocalDate startDate, LocalDate endDate) {
        return parcelRepository.findByDeliveryDateBetween(startDate, endDate)
                .stream()
                .map(ParcelEntity::getFee)
                .reduce(0.0, Double::sum);
    }
}
