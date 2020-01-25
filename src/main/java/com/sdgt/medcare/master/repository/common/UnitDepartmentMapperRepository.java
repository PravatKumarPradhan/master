package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.unit.UnitDepartmentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitDepartmentMapperRepository  extends UnitUtilRepository<UnitDepartmentMapper,Long> {

	List<UnitDepartmentMapper> findByUnitMasterId(Long unitMaster);


}
