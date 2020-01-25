package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class UnitMasterService extends BaseService<UnitMaster> {
    @Autowired
    private UnitMasterRepository unitMasterRepository;

    /**
     * Parametrized Constructor.
     *
     * @param unitMasterRepository instance of {@link UnitMasterRepository}
     */
    public UnitMasterService(final UnitMasterRepository unitMasterRepository) {
        this.unitMasterRepository = unitMasterRepository;
    }

    /**
     * Finds the {@link UnitMaster} for the given unit code.
     *
     * @param unitCode a valid unit code from the {@link UnitMaster}.
     * @return a valid {@link UnitMaster} object. Null if unit code not found.
     */
    public UnitMaster findByCode(final String unitCode) {
        return unitMasterRepository.findByCode(unitCode);
    }
}
