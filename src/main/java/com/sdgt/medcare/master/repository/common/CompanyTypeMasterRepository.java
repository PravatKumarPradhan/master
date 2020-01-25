package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.CompanyTypeMaster;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Repository
public interface CompanyTypeMasterRepository extends MastersBaseRepository<CompanyTypeMaster, Long> {
    /**
     * Find {@link CompanyTypeMaster} by code.
     *
     * @param code a valid code.
     * @return {@link CompanyTypeMaster} if found. Else null.
     */
    CompanyTypeMaster findByCode(final String code);
}
