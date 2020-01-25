package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.billing.packageservice.PackageMaster;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageTariffConfigurationMaster;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
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
public interface PackageTariffConfigurationMasterRepository extends MastersBaseRepository<PackageTariffConfigurationMaster, Long> {
    /**
     * Get list of {@link PackageTariffConfigurationMaster} by given.
     * @param packageMaster valid {@link PackageMaster}
     * @return list of {@link PackageTariffConfigurationMaster}
     */
    List<PackageTariffConfigurationMaster> findAllByPackageMaster(final PackageMaster packageMaster);

    @Query("select ptcm from PackageTariffConfigurationMaster ptcm where ptcm.packageMaster in :packageMasterList and ptcm.financialClassMaster=:financialClassMaster and ptcm.tariffMaster =:tariffMaster and ptcm.visitTypeMaster=:visitTypeMaster")
    List<PackageTariffConfigurationMaster> findByFilter(final @Param("financialClassMaster") FinancialClassMaster financialClassMaster,
                                                        final @Param("tariffMaster") TariffMaster tariffMaster,
                                                        final @Param("visitTypeMaster") VisitTypeMaster visitTypeMaster,
                                                        final @Param("packageMasterList") List<PackageMaster> packageMaster);

    List<PackageTariffConfigurationMaster> findByPackageMaster(final PackageMaster packageMaster);
}
