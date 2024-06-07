package com.arti.inventory.mission.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arti.inventory.mission.backend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
