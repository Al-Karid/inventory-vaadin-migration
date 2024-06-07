package com.arti.inventory.auto.backend.repository;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arti.inventory.auto.backend.model.MileageHistory;

@Repository
public interface MileageHistoryRepository extends JpaRepository<MileageHistory, Long>{
    
    default public Collection<MileageHistory> findAllByVehiculeId(long vehiculeId){
        return findAllByVehiculeId(vehiculeId, Sort.by(Sort.Direction.DESC, "statementDate"));
    }

    public Collection<MileageHistory> findAllByVehiculeId(long vehiculeId, Sort sort);
}
