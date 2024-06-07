package com.arti.inventory.mission.backend.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import com.arti.inventory.mission.backend.model.Member;
import com.arti.inventory.mission.backend.model.Mission;
import com.arti.inventory.mission.backend.model.Perdiem;
import com.arti.inventory.mission.backend.repository.MemberRepository;

@Service
public class MemberService implements CrudListener<Member> {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PerdiemService perdiemService;

    public Collection<Member> findMissionMembers(Long missionId) {
        Collection<Member> members = repository.findAllByMissionId(missionId);
        // members.forEach(member -> {
        //     member.setNumberOfDays(MissionService.computeNumberOfDays(member.getDateOfDeparture(), member.getDateOfReturn()));
        //     Perdiem perdiem = perdiemService.getMemberPerdiem(member);
        //     member.setHotelFees(perdiem.getHotelFees());
        //     member.setRessortExpenses(perdiem.getRessortExpenses());
        //     member.setTotalBudget(computeMemberTotalBudget(member));
        // });
        return members;
    }

    @Override
    public Collection<Member> findAll() {
        return repository.findAll();
    }

    @Override
    public Member add(Member member) {
        return repository.save(member);
    }

    public Member add(Member member, Mission mission) {
        member.setMission(mission);
        member.setNumberOfDays(MissionService.computeNumberOfDays(member.getDateOfDeparture(), member.getDateOfReturn()));
        //TODO compute all dynamic fields
        Perdiem perdiem = perdiemService.getMemberPerdiem(member);
        member.setHotelFees(perdiem.getHotelFees());
        member.setRessortExpenses(perdiem.getRessortExpenses());
        member.setTotalBudget(computeMemberTotalBudget(member));
        return repository.save(member);
    }

    private Long computeMemberTotalBudget(Member member) {
        Long totalBudget = 0L;
        totalBudget += member.getHotelFees() * member.getNumberOfDays();
        totalBudget += member.getRessortExpenses() * member.getNumberOfDays();
        totalBudget += member.getMobilityGasFees() * member.getNumberOfDays();
        return totalBudget;
    }

    @Override
    public Member update(Member domainObjectToUpdate) {
        domainObjectToUpdate.setNumberOfDays(MissionService.computeNumberOfDays(domainObjectToUpdate.getDateOfDeparture(), domainObjectToUpdate.getDateOfReturn()));
        return repository.save(domainObjectToUpdate);
    }

    @Override
    public void delete(Member domainObjectToDelete) {
        repository.delete(domainObjectToDelete);
    }

}
