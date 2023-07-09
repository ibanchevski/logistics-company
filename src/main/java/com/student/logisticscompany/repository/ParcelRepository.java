package com.student.logisticscompany.repository;

import com.student.logisticscompany.entity.ParcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
//    @Query(value = "SELECT SUM(fee) from parcels where is_delivered=true and delivery_date <= :endDate", nativeQuery = true)
//    double calculatePeriodRevenue(LocalDate startDate, LocalDate endDate);

    List<ParcelEntity> findByDeliveryDateBetween(LocalDate startDate, LocalDate endDate);
    List<ParcelEntity> findAllByDeliveredByNotNull();
    List<ParcelEntity> findAllByDeliveredByIsNull();
}
