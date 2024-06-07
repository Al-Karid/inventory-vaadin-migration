package com.arti.inventory.device.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arti.inventory.device.backend.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
