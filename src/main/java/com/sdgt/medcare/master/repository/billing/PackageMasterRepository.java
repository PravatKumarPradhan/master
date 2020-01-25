package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.billing.packageservice.PackageMaster;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageTypeMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.repository.common.MastersBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface PackageMasterRepository extends MastersBaseRepository<PackageMaster, Long> {
    /**
     * Get all {@link PackageMaster} by unit code.
     * @param unitMaster a valid {@link UnitMaster} for given unit code.
     * @return list of all {@link PackageMaster}.
     */
    List<PackageMaster> findAllByUnitMaster(UnitMaster unitMaster);

    /**
     * Get all package by given {@link UnitServiceMapper}.
     * @param unitServiceMapper valid {@link UnitServiceMapper}
     * @return {@link PackageMaster} if found. Else null.
     */
    PackageMaster findByUnitServiceMapper(UnitServiceMapper unitServiceMapper);

    /**
     * Get {@link PackageMaster} by code.
     *
     * @param code a valid service code of package type.
     * @return {@link PackageMaster} if found. Else null.
     */
    @Query("select pm from PackageMaster pm where pm.code = :code")
    PackageMaster findByCode(final @Param("code") String code);

    /**
     * Find all {@link PackageMaster} by given.
     *
     * @param packageTypeMaster given {@link PackageTypeMaster} object.
     * @return {@link PackageMaster} list.
     */
    List<PackageMaster> findAllByPackageTypeMaster(PackageTypeMaster packageTypeMaster);
}
