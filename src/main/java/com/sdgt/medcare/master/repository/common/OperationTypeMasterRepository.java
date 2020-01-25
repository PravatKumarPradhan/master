package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.OperationTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface OperationTypeMasterRepository extends JpaRepository<OperationTypeMaster, Long> {
    /**
     * Get {@link OperationTypeMaster} by given code.
     *
     * @param code a valid from {@link OperationTypeMaster}
     * @return the {@link OperationTypeMaster} object if found. Else null.
     */
    OperationTypeMaster findByCode(final String code);
}
