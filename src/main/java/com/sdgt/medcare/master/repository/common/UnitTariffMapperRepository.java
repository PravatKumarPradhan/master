package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitTariffMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface UnitTariffMapperRepository extends MastersBaseRepository<UnitTariffMapper, Long> {

    /**
     * Gets all {@link UnitTariffMapper} by given {@link UnitMaster}
     * @param unitMaster a valid {@link UnitMaster}
     * @return a list of {@link UnitTariffMapper}
     */
    List<UnitTariffMapper> findAllByUnitMaster(final UnitMaster unitMaster);
}
