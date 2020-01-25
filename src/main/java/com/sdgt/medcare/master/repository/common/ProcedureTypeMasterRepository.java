package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.ProcedureMaster;
import com.sdgt.medcare.master.entity.global.ProcedureTypeMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface ProcedureTypeMasterRepository extends JpaRepository<ProcedureTypeMaster, Long> {
    /**
     * Get {@link ProcedureTypeMaster} by given code.
     *
     * @param code a valid from {@link ProcedureTypeMaster}
     * @return the {@link ProcedureTypeMaster} object if found. Else null.
     */
    ProcedureTypeMaster findByCode(final String code);



}
