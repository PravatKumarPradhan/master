package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface FinancialClassMasterRepository extends JpaRepository<FinancialClassMaster, Long> {
    /**
     * Gets {@link FinancialClassMaster} by given code.
     *
     * @param financialClassCode a valid code from {@link FinancialClassMaster}
     * @return the {@link FinancialClassMaster} object.
     */
    FinancialClassMaster findByCode(String financialClassCode);
}
