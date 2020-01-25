package com.sdgt.medcare.master.service.common;

import com.sdgt.medcare.master.dto.billing.UnitWiseBillingChargesDTO;
import com.sdgt.medcare.master.dto.billing.UnitWiseService;
import com.sdgt.medcare.master.dto.otims.OtimsProcedureServiceRequest;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class OtimsService {
    @Autowired private UnitMasterService unitMasterService;
    @Autowired private UnitServiceMapperService unitServiceMapperService;

    private Logger logger = LoggerFactory.getLogger(OtimsService.class);
    /**
     * Service to find list of {@link UnitWiseService} by given filter {@link UnitWiseBillingChargesDTO}.
     * Gets the list of active services of procedure type for given unit and also if in valid date range.
     *
     * @param request a valid {@link OtimsProcedureServiceRequest} object.
     * @return a list of {@link UnitWiseService}.
     * @throws {@link IllegalArgumentException} if request data is not valid.
     */
    public List<UnitWiseService> findAllProcedureService(final OtimsProcedureServiceRequest request) {
        if (!validateDTO(request)) {
            logger.error("Unit header missing. {}", request);
            throw new IllegalArgumentException("Unit header missing");
        }

        final UnitMaster unitMaster = unitMasterService.findByCode(request.getUnitCode());
        if (unitMaster == null) {
            logger.warn("unitMaster not found.");
            return Collections.emptyList();
        }

        final List<UnitServiceMapper> unitServiceMapperList = unitServiceMapperService.findAllProcedureService(unitMaster);

        if (unitServiceMapperList == null || CollectionUtils.isEmpty(unitServiceMapperList)) {
            logger.warn("No unit wise service for the given unitCode {}", request.getUnitCode());
            return Collections.emptyList();
        }

        //FIXME: implement streams
        final List<UnitWiseService> unitWiseServiceList = new ArrayList<>();
        for (final UnitServiceMapper unitServiceMapper : unitServiceMapperList) {
            if (unitServiceMapper.getActive() && unitServiceMapper.getServiceMaster().getActive()) {
                final UnitWiseService unitWiseService = new UnitWiseService();
                unitWiseService.setServiceCode(unitServiceMapper.getServiceMaster().getCode());
                unitWiseService.setServiceName(unitServiceMapper.getServiceMaster().getDesc());
                unitWiseService.setIsDoctorRequired(unitServiceMapper.getIsDoctorRequired());
                unitWiseService.setServiceId(unitServiceMapper.getServiceMaster().getId());
                unitWiseService.setServiceMaster(unitServiceMapper.getServiceMaster());
                unitWiseService.setUnitServiceMapperId(unitServiceMapper.getId());
                unitWiseService.setUnitServiceMapper(unitServiceMapper);
                unitWiseService.setServiceMaster(unitServiceMapper.getServiceMaster());
                unitWiseService.setGroupCode(BaseMaster.getCode(unitWiseService.getServiceMaster().getGroupMaster()));
                unitWiseService.setGroupDesc(BaseMaster.getDesc(unitWiseService.getServiceMaster().getGroupMaster()));
                unitWiseService.setSubGroupCode(BaseMaster.getCode(unitWiseService.getServiceMaster().getSubGroupMaster()));
                unitWiseService.setSubGroupDesc(BaseMaster.getDesc(unitWiseService.getServiceMaster().getSubGroupMaster()));
                unitWiseService.setGroupId(unitWiseService.getServiceMaster().getGroupMaster().getId());
                unitWiseService.setSubGroupId(unitWiseService.getServiceMaster().getSubGroupMaster().getId());
                unitWiseService.setIsDoctorRequired(unitServiceMapper.getIsDoctorRequired());

                unitWiseServiceList.add(unitWiseService);
            }
        }

        return unitWiseServiceList;
    }

    /**
     * Validates the dto.
     *
     * @param request given {@link UnitWiseBillingChargesDTO} object.
     * @return true if valid. Else false.
     */
    private boolean validateDTO(final OtimsProcedureServiceRequest request) {
        if (request == null) {
            return false;
        }
        return true;
    }
}
