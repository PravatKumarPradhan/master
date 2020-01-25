package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.ProcedureResourceConfigurationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureResourceConfigurationRepository extends JpaRepository<ProcedureResourceConfigurationMaster,Long>{

}
