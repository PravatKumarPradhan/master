package com.sdgt.medcare.master.repository.common;


import com.sdgt.medcare.master.entity.unit.DependentUnitServiceMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepenedentUnitServiceMapperRepository extends JpaRepository<DependentUnitServiceMapper,Long> {
}
