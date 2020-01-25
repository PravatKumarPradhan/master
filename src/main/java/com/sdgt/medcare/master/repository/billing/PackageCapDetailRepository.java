package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.billing.packageservice.PackageCapDetail;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageTariffConfigurationMaster;
import com.sdgt.medcare.master.repository.common.MastersBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface PackageCapDetailRepository extends MastersBaseRepository<PackageCapDetail, Long> {
    /**
     * Get {@link PackageCapDetail} by given.
     *
     * @param packageTariffConfigurationMaster a valid {@link PackageTariffConfigurationMaster} object.
     * @return list of {@link PackageCapDetail} objects.
     */
    List<PackageCapDetail> findAllByPackageTariffConfigurationMaster(final PackageTariffConfigurationMaster packageTariffConfigurationMaster);
}
