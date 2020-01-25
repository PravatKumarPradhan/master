package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.AnalysisTypeMaster;
import com.sdgt.medcare.master.entity.unit.UomTypeMaster;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sachin Raghuwanshi
 */
@Repository
public interface UomTypeRepository extends MastersBaseRepository<UomTypeMaster, Long> {

}
