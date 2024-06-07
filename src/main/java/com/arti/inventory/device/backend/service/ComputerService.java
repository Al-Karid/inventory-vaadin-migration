package com.arti.inventory.device.backend.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import com.arti.inventory.device.backend.model.Computer;
import com.arti.inventory.device.backend.repository.ComputerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComputerService implements CrudListener<Computer>, DeviceService {

    @Autowired
    private ComputerRepository repository;

    @Override
    public Computer add(Computer computer) {
        return repository.save(computer);
    }

    @Override
    public void delete(Computer computer) {
        repository.delete(computer);
    }

    @Override
    public Collection<Computer> findAll() {
        Collection<Computer> computers = repository.findAll();
        computers.forEach(computer -> {
            Integer ipToInt = AddressService.IpToInteger(computer.getIp());
            computer.setIpToInteger(ipToInt);
        });
        return computers;
    }

    @Override
    public Computer update(Computer computer) {
        return repository.save(computer);
    }

    @Override
    public Integer getDeviceCount() {
        return repository.findAll().size();
    }
}
