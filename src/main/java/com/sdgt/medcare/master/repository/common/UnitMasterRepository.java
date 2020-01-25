package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface UnitMasterRepository extends MastersBaseRepository<UnitMaster, Long> {

    /**
     * Finds the {@link UnitMaster} by given unit code.
     *
     * @param code a valid code defined in {@link UnitMaster}.
     * @return the {@link UnitMaster} object.
     */
     UnitMaster findByCode(String code);
}
