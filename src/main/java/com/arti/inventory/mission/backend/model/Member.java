package com.arti.inventory.mission.backend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;
    private Long hotelFees = 0L;
    private Long ressortExpenses = 0L;
    @Column(name = "departure_date")
    private Date dateOfDeparture;
    @Column(name = "return_date")
    private Date dateOfReturn;
    private Long numberOfDays = 0L;
    @Enumerated(EnumType.STRING)
    private Transportation transportation = Transportation.CAR;
    @Enumerated(EnumType.STRING)
    private Mobility mobility = Mobility.PERSONAL_CAR;
    private Long mobilityGasFees = 0L;
    private Long totalBudget = 0L;
}
