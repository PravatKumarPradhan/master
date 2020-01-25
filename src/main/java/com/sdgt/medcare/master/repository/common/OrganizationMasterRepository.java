package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface OrganizationMasterRepository extends MastersBaseRepository<OrganizationMaster, Long> {
    /**
     * Get {@link OrganizationMaster} by given code.
     *
     * @param code a valid code from {@link OrganizationMaster}
     * @return the {@link OrganizationMaster} if found, else null.
     */
    OrganizationMaster findByCode(final String code);
}
