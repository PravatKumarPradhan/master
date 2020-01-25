package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.org.GradeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Repository
public interface GradeMasterRepository extends JpaRepository<GradeMaster, Long> {
    /**
     * Find {@link GradeMaster} by code.
     *
     * @param code a valid code.
     * @return {@link GradeMaster} if found. Else null.
     */
    GradeMaster findByCode(final String code);
}
