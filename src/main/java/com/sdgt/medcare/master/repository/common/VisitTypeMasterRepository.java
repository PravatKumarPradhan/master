package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface VisitTypeMasterRepository extends JpaRepository<VisitTypeMaster, Long> {
    /**
     * Gets {@link VisitTypeMaster} by given code.
     *
     * @param visitTypeCode a valid code from {@link VisitTypeMaster}
     * @return the {@link VisitTypeMaster} object.
     */
    VisitTypeMaster findByCode(final String visitTypeCode);
}
