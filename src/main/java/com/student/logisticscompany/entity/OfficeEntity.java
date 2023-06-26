package com.student.logisticscompany.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offices")
public class OfficeEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @JoinColumn(name = "added_by", nullable = false)
    @ManyToOne
    private UserEntity addedBy;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateAdded;
}
