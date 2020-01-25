package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.DiscountTypeMaster;

public interface DiscountTypeMasterRepository extends MastersBaseRepository<DiscountTypeMaster, Long> {
    /**
     * Get {@link DiscountTypeMaster} by given code.
     *
     * @param code a valid code from {@link DiscountTypeMaster} table.
     * @return the {@link DiscountTypeMaster} object if found, else null.
     */
    DiscountTypeMaster findByCode(final String code);
}
