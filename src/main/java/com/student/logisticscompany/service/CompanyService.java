package com.student.logisticscompany.service;

import com.student.logisticscompany.entity.CompanyEntity;

import java.time.LocalDate;

public interface CompanyService {
    void addRevenue(double revenue);

    CompanyEntity getCompany();

    double calculateRveneuForPriod(LocalDate startDate, LocalDate endDate);
}
