package com.arti.inventory.mission.backend.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import com.arti.inventory.mission.backend.model.Member;
import com.arti.inventory.mission.backend.model.Perdiem;
import com.arti.inventory.mission.backend.repository.PerdiemRepository;

@Service
public class PerdiemService implements CrudListener<Perdiem> {

    @Autowired
    private PerdiemRepository repository;

    @Override
    public Collection<Perdiem> findAll() {
        return repository.findAll();
    }

    @Override
    public Perdiem add(Perdiem domainObjectToAdd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public Perdiem update(Perdiem domainObjectToUpdate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Perdiem domainObjectToDelete) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public Perdiem getByCode(String code) {
        return repository.findByCode(code);
    }

    public Perdiem getMemberPerdiem(Member member) {
        String missionType = member.getMission().getType().name();
        String employeeCategory = member.getEmployee().getCategory().name();
        String memberPerdiemCode = missionType + "-" + employeeCategory;
        return repository.findByCode(memberPerdiemCode);
    }





}
