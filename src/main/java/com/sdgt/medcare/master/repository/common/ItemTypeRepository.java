package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.AnalysisTypeMaster;
import com.sdgt.medcare.master.entity.global.ItemTypeMaster;
import com.sdgt.medcare.master.repository.common.MastersBaseRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sachin Raghuwanshi
 */
@Repository
public interface ItemTypeRepository extends MastersBaseRepository<ItemTypeMaster, Long> {

}
