package com.arti.inventory.device.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arti.inventory.device.backend.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
