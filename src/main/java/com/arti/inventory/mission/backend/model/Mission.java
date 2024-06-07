package com.arti.inventory.mission.backend.model;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String subject;
    @Column(name = "mission_type")
    @Enumerated(EnumType.STRING)
    private MissionType type = MissionType.INLAND;
    @NotNull
    private String location;
    @NotNull
    @Column(name = "departure_date")
    private Date dateOfDeparture;
    @NotNull
    @Column(name = "return_date")
    private Date dateOfReturn;
    private Long numberOfDays;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    private Long totalBudget;
    private transient Collection<Member> members;
}
