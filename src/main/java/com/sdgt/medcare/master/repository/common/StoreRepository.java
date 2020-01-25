package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.unit.StoreMaster;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Sachin Raghuwanshi
 */
@Repository
public interface StoreRepository extends MastersBaseRepository<StoreMaster, Long> {

    /**
     *
     @param code
     @return
     */
    @Query("Select sm from StoreMaster sm where sm.code=:code")
    StoreMaster findByCode(@Param("code") String code);
}
