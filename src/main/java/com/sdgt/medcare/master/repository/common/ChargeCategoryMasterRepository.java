package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.billing.ChargeCategoryMaster;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeCategoryMasterRepository extends MastersBaseRepository<ChargeCategoryMaster, Long> {
    /**
     * Find {@link ChargeCategoryMaster} by code.
     *
     * @param code a valid code.
     * @return {@link ChargeCategoryMaster} if found. Else null.
     */
    ChargeCategoryMaster findByCode(final String code);
}
