package com.arti.inventory.device.backend.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.CrudListener;

import com.arti.inventory.device.backend.model.DeviceOnlineStatus;
import com.arti.inventory.device.backend.model.DeviceStats;
import com.arti.inventory.device.backend.model.Phone;
import com.arti.inventory.device.backend.repository.PhoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneService implements CrudListener<Phone>, DeviceService {

    @Autowired
    private PhoneRepository repository;

    @Override
    public Collection<Phone> findAll() {
        Collection<Phone> phones = repository.findAll();
        phones.forEach(phone -> setDetails(phone));
        return phones;
    }

    @Override
    public Phone add(Phone phone) {
        return repository.save(phone);
    }

    @Override
    public Phone update(Phone phone) {
        return repository.save(phone);
    }

    @Override
    public void delete(Phone phone) {
        repository.delete(phone);
    }

    @Override
    public Integer getDeviceCount() {
        return repository.findAll().size();
    }

    public Phone setDetails(Phone phone) {
        if (phone == null) {
            System.err.println("Phone is null");
            return null;
        }
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        //TODO Fix the url
        DeviceOnlineStatus status = restTemplate.getForObject("http://api:3000/devices/status?ip=" + phone.getIp(), DeviceOnlineStatus.class);
        phone.setOnline(status.status());
        return phone;
    }

    public DeviceStats getStats() {
        Collection<Phone> phones = repository.findAll();
        phones.forEach(phone -> setDetails(phone));
        Collection<Phone> onlinePhones = phones.stream().map(this::setDetails)
                                                .filter(phone -> phone.getOnline())
                                                .collect(Collectors.toList());
        return new DeviceStats(phones.size(), onlinePhones.size(), phones.size() - onlinePhones.size());
    }

}
