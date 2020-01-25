package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<UnitMaster, Long> {

    List  <UnitMaster> findByCode(String code);


    List <UnitMaster> findByCode(Long unitId);

}
