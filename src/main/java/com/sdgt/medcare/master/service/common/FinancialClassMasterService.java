package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.repository.common.FinancialClassMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class FinancialClassMasterService extends BaseService<FinancialClassMaster> {
    @Autowired
    private FinancialClassMasterRepository financialClassMasterRepository;

    /**
     * Gets {@link FinancialClassMaster} object by given financial code.
     *
     * @param financialClassCode a valid code from {@link FinancialClassMaster}
     * @return the {@link FinancialClassMaster}
     */
    public FinancialClassMaster findByCode(final String financialClassCode) {
        return financialClassMasterRepository.findByCode(financialClassCode);
    }
}
