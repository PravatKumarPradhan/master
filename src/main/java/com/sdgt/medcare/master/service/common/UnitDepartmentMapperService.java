package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.unit.UnitDepartmentMapper;
import com.sdgt.medcare.master.repository.common.UnitDepartmentMapperRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Pravat Kumar Pradhan
 */
@Service
public class UnitDepartmentMapperService extends BaseService<UnitDepartmentMapper> {


    private UnitDepartmentMapperRepository unitDepartmentMapperRepository;

    public UnitDepartmentMapperService(UnitDepartmentMapperRepository unitDepartmentMapperRepository){
        unitDepartmentMapperRepository=unitDepartmentMapperRepository;
    }

    /**
     *
     * @param unitMaster
     * @param pageable
     * @return
     */
    public Page<UnitDepartmentMapper> getDepartmentByUnit(Long unitMaster,Pageable pageable){
        return unitDepartmentMapperRepository.findByUnitMasterId(unitMaster,pageable);
    }

    /**
     *
     * @param unitMaster
     * @param pageable
     * @return
     */
    public Page<UnitDepartmentMapper> getDepartmentByUnitId(Long unitMaster,Pageable pageable){
        return unitDepartmentMapperRepository.findByUnitMasterId(unitMaster,pageable);
    }
}
