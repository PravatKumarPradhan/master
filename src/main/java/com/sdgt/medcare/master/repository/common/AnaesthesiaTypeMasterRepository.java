package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.AnaesthesiaTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface AnaesthesiaTypeMasterRepository extends JpaRepository<AnaesthesiaTypeMaster, Long> {
    /**
     * Finds the {@link AnaesthesiaTypeMaster} by given dept code.
     *
     * @param code a valid code defined in {@link AnaesthesiaTypeMaster}.
     * @return the {@link AnaesthesiaTypeMaster} object.
     */
    AnaesthesiaTypeMaster findByCode(String code);
}
