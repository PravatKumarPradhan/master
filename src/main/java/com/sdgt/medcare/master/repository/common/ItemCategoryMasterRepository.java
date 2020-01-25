package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.ItemCategoryMaster;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface ItemCategoryMasterRepository extends MastersBaseRepository<ItemCategoryMaster, Long> {
    /**
     * Get {@link ItemCategoryMaster} by given code.
     * @param code a valid code.
     * @return {@link ItemCategoryMaster} if found. Else null.
     */
    ItemCategoryMaster findByCodeIgnoreCase(final String code);

    /**
     * Find {@link ItemCategoryMaster} by given id or code.
     *
     * @param id a valid id. Nullable.
     * @param code a valid code. Nullable
     * @return {@link ItemCategoryMaster} if found. Else null.
     */
    ItemCategoryMaster findByIdOrCode(final Long id, final String code);
}
