package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.CompanyMaster;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Repository
public interface CompanyMasterRepository extends MastersBaseRepository<CompanyMaster, Long> {
    /**
     * Find {@link CompanyMaster} by code.
     *
     * @param code a valid code.
     * @return {@link CompanyMaster} if found. Else null.
     */
    CompanyMaster findByCode(final String code);
}
