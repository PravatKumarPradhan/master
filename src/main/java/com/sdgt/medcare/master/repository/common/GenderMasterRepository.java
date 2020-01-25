package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.GenderMaster;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface GenderMasterRepository extends MastersBaseRepository<GenderMaster, Long> {
    /**
     * Get {@link GenderMaster} by given code.
     *
     * @param code a valid code from {@link GenderMaster}
     * @return the {@link GenderMaster} if found, else null.
     */
    GenderMaster findByCode(final String code);
}
