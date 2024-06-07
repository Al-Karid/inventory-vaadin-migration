package com.arti.inventory.auto.backend.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import com.arti.inventory.auto.backend.model.MileageHistory;
import com.arti.inventory.auto.backend.repository.MileageHistoryRepository;

@Service
public class MileageHistoryService implements CrudListener<MileageHistory> {

    @Autowired
    private MileageHistoryRepository repository;

    @Override
    public Collection<MileageHistory> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "statementDate"));
    }

    @Override
    public MileageHistory add(MileageHistory domainObjectToAdd) {
        return repository.save(domainObjectToAdd);
    }

    @Override
    public MileageHistory update(MileageHistory domainObjectToUpdate) {
        return repository.save(domainObjectToUpdate);
    }

    @Override
    public void delete(MileageHistory domainObjectToDelete) {
        repository.delete(domainObjectToDelete);
    }

    public Collection<MileageHistory> getVehiculeMileages(long vehiculeId) {
        return repository.findAllByVehiculeId(vehiculeId);
    }

}
