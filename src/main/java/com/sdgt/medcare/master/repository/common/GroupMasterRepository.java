package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.GroupMaster;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface GroupMasterRepository  extends MastersBaseRepository<GroupMaster, Long> {
    /**
     * Get {@link GroupMaster}
     * @param code a valid code.
     * @return {@link GroupMaster} if found. Else null.
     */
    GroupMaster findByCodeIgnoreCase(final String code);
}
