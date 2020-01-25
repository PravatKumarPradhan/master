package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 * @@author Pravat Kumar Pradhan
 */
@Repository
public interface UnitTariffServiceRateMasterRepository extends JpaRepository<UnitTariffServiceRateMaster, Long> , JpaSpecificationExecutor<UnitTariffServiceRateMaster> {
    /**
     * Get the {@link UnitTariffServiceRateMaster} by given {@link FinancialClassMaster}, {@link VisitTypeMaster}, {@link TariffMaster}, {@link UnitServiceMapper} objects.
     *
     * @param financialClassMaster a valid {@link FinancialClassMaster} object.
     * @param visitTypeMaster a valid {@link VisitTypeMaster} object.
     * @param tariffMaster a valid {@link TariffMaster} object.
     * @param unitServiceMapper a valid {@link UnitServiceMapper} object.
     * @return the {@link UnitTariffServiceRateMaster} object.
     */
    List<UnitTariffServiceRateMaster> findAllByFinancialClassMasterAndVisitTypeMasterAndTariffMasterAndUnitServiceMapperAndActive(final FinancialClassMaster financialClassMaster,
                                                                                                                         final VisitTypeMaster visitTypeMaster,
                                                                                                                         final TariffMaster tariffMaster,
                                                                                                                         final UnitServiceMapper unitServiceMapper,
                                                                                                                                  final Boolean active);

    /**
     * Get the {@link UnitTariffServiceRateMaster} by given {@link FinancialClassMaster}, {@link VisitTypeMaster}, {@link TariffMaster}, {@link UnitServiceMapper} objects.
     *
     * @param financialClassMaster a valid {@link FinancialClassMaster} object.
     * @param visitTypeMaster a valid {@link VisitTypeMaster} object.
     * @param tariffMaster a valid {@link TariffMaster} object.
     * @param unitMaster a valid {@link UnitMaster} object.
     * @return the {@link UnitTariffServiceRateMaster} object.
     */
    List<UnitTariffServiceRateMaster> findAllByFinancialClassMasterAndVisitTypeMasterAndTariffMasterAndUnitMasterAndActive(final FinancialClassMaster financialClassMaster,
                                                                                                                                  final VisitTypeMaster visitTypeMaster,
                                                                                                                                  final TariffMaster tariffMaster,
                                                                                                                                  final UnitMaster unitMaster,
                                                                                                                                  final Boolean active);

    List<UnitTariffServiceRateMaster> findAllByUnitServiceMapperAndActive(final UnitServiceMapper unitServiceMapper, final Boolean active);


    Page<UnitTariffServiceRateMaster> findAllByUnitMaster(UnitMaster unitMaster, Pageable pageable);
}
