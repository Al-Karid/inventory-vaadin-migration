package com.arti.inventory.mission.backend.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import com.arti.inventory.mission.backend.model.Employee;
import com.arti.inventory.mission.backend.repository.EmployeeRepository;

@Service
public class EmployeeService implements CrudListener<Employee> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Collection<Employee> findAll() {
        return repository.findAll();   
    }

    @Override
    public Employee add(Employee domainObjectToAdd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public Employee update(Employee domainObjectToUpdate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Employee domainObjectToDelete) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
