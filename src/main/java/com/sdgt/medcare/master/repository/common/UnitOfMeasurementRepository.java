package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.AnalysisTypeMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.BedMaster;
import com.sdgt.medcare.master.entity.unit.UnitOfMeasurementMaster;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Sachin Raghuwanshi
 */
@Repository
public interface UnitOfMeasurementRepository extends MastersBaseRepository<UnitOfMeasurementMaster, Long> {

    /**
     *
     @param id
     @return
     */
    @Query("select um from UnitOfMeasurementMaster um INNER JOIN ItemMaster im on um.itemMaster=im.id AND um.active= true WHERE im.id= :id")
    List<UnitOfMeasurementMaster> findByItemId(@Param("id") Long id);

}
