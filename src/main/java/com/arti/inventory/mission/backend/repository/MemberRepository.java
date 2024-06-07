package com.arti.inventory.mission.backend.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arti.inventory.mission.backend.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

    public Collection<Member> findAllByMissionId(Long memberId);
}
