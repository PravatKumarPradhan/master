package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.ItemCategoryMaster;
import com.sdgt.medcare.master.entity.global.ItemGroupMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface ItemMasterRepository extends MastersBaseRepository<ItemMaster,Long>, ItemMasterRepositoryCustom{
    /**
     * Get {@link ItemMaster} by given code.
     * @param code a valid code.
     * @return {@link ItemMaster} if found. Else null.
     */
    ItemMaster findByCodeIgnoreCase(final String code);

    /**
     * Get {@link ItemMaster} list by given codes.
     * @param codes a valid code.
     * @return list of {@link ItemMaster} if found. Else null.
     */
    Collection<ItemMaster> findAllByCode(final Collection<String> codes);

    /**
     * Find {@link ItemMaster} by given id or code.
     *
     * @param id a valid id. Nullable.
     * @param code a valid code. Nullable
     * @return {@link ItemMaster} if found. Else null.
     */
    ItemMaster findByIdOrCode(final Long id, final String code);

    /**
     * Find {@link ItemMaster} by given {@link ItemGroupMaster}
     *
     * @param itemGroupMaster given.
     * @return list of {@link ItemMaster}
     */
    Collection<ItemMaster> findByItemGroupMaster(final ItemGroupMaster itemGroupMaster);

    /**
     * Find {@link ItemMaster} by given {@link ItemCategoryMaster}
     *
     * @param itemCategoryMaster given.
     * @return list of {@link ItemMaster}
     */
    Collection<ItemMaster> findByItemCategoryMaster(final ItemCategoryMaster itemCategoryMaster);
}
