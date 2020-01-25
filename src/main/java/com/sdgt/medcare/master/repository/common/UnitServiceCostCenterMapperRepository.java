package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceCostCenterMapper;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface UnitServiceCostCenterMapperRepository extends JpaRepository<UnitServiceCostCenterMapper, Long> {
    /**
     * Finds all {@link UnitServiceCostCenterMapper} by given {@link UnitMaster}.
     *
     * @param unitServiceMapper a valid {@link UnitServiceMapper} object.
     * @return a list of {@link UnitServiceCostCenterMapper}.
     */
    List<UnitServiceCostCenterMapper> findAllByUnitServiceMapper(final UnitServiceMapper unitServiceMapper);
}
