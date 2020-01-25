package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.repository.common.TariffMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class TariffMasterService extends BaseService<TariffMaster> {
    @Autowired
    private TariffMasterRepository tariffMasterRepository;

    /**
     * Gets {@link TariffMaster} object by given financial code.
     *
     * @param tariffCode a valid code from {@link TariffMaster}
     * @return the {@link TariffMaster}
     */
    public TariffMaster findByCode(final String tariffCode) {
        return tariffMasterRepository.findByCode(tariffCode);
    }
}
