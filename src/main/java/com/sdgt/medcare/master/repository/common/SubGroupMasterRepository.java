package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.SubGroupMaster;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
public interface SubGroupMasterRepository  extends MastersBaseRepository<SubGroupMaster, Long> {
    /**
     * Get {@link SubGroupMaster}
     * @param code a valid code.
     * @return {@link SubGroupMaster} if found. Else null.
     */
    SubGroupMaster findByCodeIgnoreCase(final String code);
}
