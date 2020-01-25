package com.sdgt.medcare.master.service;


import com.core.base.rest.util.HttpUtils;
import com.core.service.BaseService;
import com.sdgt.medcare.master.dto.UpgradeExceptionDTO;
import com.sdgt.medcare.master.entity.billing.UpgradeExceptionMaster;
import com.sdgt.medcare.master.repository.UpgradeExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UpgradeExceptionService extends BaseService<UpgradeExceptionMaster> {

    @Autowired
    UpgradeExceptionRepository upgradeExceptionRepository;

    @Transactional
    public UpgradeExceptionDTO checkUpgradeExceptionFinancialClassExist(String subGroupMasterId,String upgradeType,String unitId,String orgId) {




        UpgradeExceptionMaster upgradeExceptionMaster=upgradeExceptionRepository.checkUpgradeExceptionFinancialClass(upgradeType,subGroupMasterId,unitId,orgId);

        UpgradeExceptionDTO upgradeExceptionDTOResponse=new UpgradeExceptionDTO();

        upgradeExceptionDTOResponse.setOrgId(orgId);
        upgradeExceptionDTOResponse.setUnitId(unitId);
        upgradeExceptionDTOResponse.setSubGroupMasterId(subGroupMasterId);
        upgradeExceptionDTOResponse.setUpgradeType(upgradeType);

        return upgradeExceptionDTOResponse;
    }
}
