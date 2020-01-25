package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceCostCenterMapper;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.repository.common.UnitServiceCostCenterMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class UnitServiceCostCenterMapperService extends BaseService<UnitServiceCostCenterMapper> {
    @Autowired
    private UnitServiceCostCenterMapperRepository unitServiceCostCenterMapperRepository;

    /**
     * Finds all {@link UnitServiceCostCenterMapper} by given {@link UnitMaster}.
     *
     * @param unitServiceMapper a valid unit in the {@link UnitServiceMapper}
     * @return an object of type {@link UnitMaster}. Else null if not found.
     */
    public List<UnitServiceCostCenterMapper> findAllByUnitServiceMapper(final UnitServiceMapper unitServiceMapper) {
        return unitServiceCostCenterMapperRepository.findAllByUnitServiceMapper(unitServiceMapper);
    }
}
