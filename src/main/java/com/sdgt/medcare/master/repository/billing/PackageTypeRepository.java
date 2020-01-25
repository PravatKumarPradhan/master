package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.billing.packageservice.PackageTypeMaster;
import com.sdgt.medcare.master.repository.common.MastersBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface PackageTypeRepository extends MastersBaseRepository<PackageTypeMaster, Long> {
    /**
     * Get {@link PackageTypeMaster} by given code.
     *
     * @param code a valid from {@link PackageTypeMaster}
     * @return the {@link PackageTypeMaster} object if found. Else null.
     */
    PackageTypeMaster findByCode(final String code);
}
