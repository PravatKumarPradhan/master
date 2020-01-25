package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.ProcedureMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository  extends JpaRepository<ProcedureMaster,Long> , JpaSpecificationExecutor<ProcedureMaster> {

    /**
     * Finds the {@link ProcedureMaster} by given code.
     *
     * @param code a valid code defined in {@link ProcedureMaster}.
     * @return the {@link ProcedureMaster} object.
     */
    ProcedureMaster findByCode(String code);

    List<ProcedureMaster> findByUnitServiceMapper(final UnitServiceMapper unitServiceMapper) ;

}
