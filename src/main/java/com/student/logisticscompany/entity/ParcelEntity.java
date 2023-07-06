package com.student.logisticscompany.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parcels")
public class ParcelEntity extends BaseEntity {
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @Column(name = "address")
    private String address;

    @Column(name = "weight")
    private double weight;

    @Column(name = "delivery_fee")
    private double fee;

    @Column(name = "is_office")
    private boolean isOffice;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private UserEntity employee;

    @ManyToOne
    @JoinColumn(name = "office_send_id")
    private OfficeEntity officeSend;

    @ManyToOne
    @JoinColumn(name = "office_receive_id")
    public OfficeEntity officeReceive;

    @Column(name = "is_delivered")
    private boolean isDelivered;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "delivered_by")
    private UserEntity deliveredBy;
}
