package com.student.logisticscompany.repository;

import com.student.logisticscompany.entity.ParcelEntity;
import org.springframework.data.repository.CrudRepository;

public interface ParcelRepository extends CrudRepository<ParcelEntity, Long> {
}
