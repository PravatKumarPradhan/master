package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.ItemGroupMaster;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface ItemGroupMasterRepository extends MastersBaseRepository<ItemGroupMaster, Long> {
    /**
     * Get {@link ItemGroupMaster} by given code.
     * @param code a valid code.
     * @return {@link ItemGroupMaster} if found. Else null.
     */
    ItemGroupMaster findByCodeIgnoreCase(final String code);

    /**
     * Find {@link ItemGroupMaster} by given id or code.
     *
     * @param id a valid id. Nullable.
     * @param code a valid code. Nullable
     * @return {@link ItemGroupMaster} if found. Else null.
     */
    ItemGroupMaster findByIdOrCode(final Long id, final String code);
}
