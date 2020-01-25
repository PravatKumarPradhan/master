package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.AnalysisTypeMaster;
import com.sdgt.medcare.master.entity.global.ConsumptionTypeMaster;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sachin Raghuwanshi
 */
@Repository
public interface ConsumptionTypeRepository extends MastersBaseRepository<ConsumptionTypeMaster, Long> {

}
