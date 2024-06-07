package com.arti.inventory.device.backend.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import com.arti.inventory.device.backend.model.Address;
import com.arti.inventory.device.backend.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService implements CrudListener<Address>, DeviceService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Collection<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address add(Address domainObjectToAdd) {
        return addressRepository.save(domainObjectToAdd);
    }

    @Override
    public Address update(Address domainObjectToUpdate) {
        return addressRepository.save(domainObjectToUpdate);
    }

    @Override
    public void delete(Address domainObjectToDelete) {
        addressRepository.delete(domainObjectToDelete);
    }

    @Override
    public Integer getDeviceCount() {
        return addressRepository.findAll().size();
    }

    public static Integer IpToInteger(String ip) {
        String[] ipArray = ip.split("\\.");
        return Integer.parseInt(ipArray[0]) * 256 * 256 * 256 + Integer.parseInt(ipArray[1]) * 256 * 256
                + Integer.parseInt(ipArray[2]) * 256 + Integer.parseInt(ipArray[3]);
    }

}
