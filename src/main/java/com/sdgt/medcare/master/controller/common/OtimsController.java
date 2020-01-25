package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.dto.billing.UnitWiseBillingChargesDTO;
import com.sdgt.medcare.master.dto.billing.UnitWiseService;
import com.sdgt.medcare.master.dto.otims.OtimsProcedureServiceRequest;
import com.sdgt.medcare.master.service.common.OtimsService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@CrossOrigin
@RestController
@RequestMapping("/otims-masters")
public class OtimsController {

    @Autowired
    private OtimsService otimsService;
    /**
     * service to find list of {@link UnitWiseService} by given filter
     * {@link UnitWiseBillingChargesDTO}
     *
     * @return Status 200. List of {@link UnitWiseService}.
     *         Status 204. If the no content found.
     */
    @GetMapping("/procedure-services")
    public ResponseEntity<List<UnitWiseService>> findAllUnitWiseServicesByFilterForCharges () {
        final OtimsProcedureServiceRequest request = new OtimsProcedureServiceRequest();
        request.setUnitCode(HttpUtils.getHeader(HttpHeaders.UNIT_CODE));

        final List<UnitWiseService> unitWiseServiceList = otimsService.findAllProcedureService(request);
        if(CollectionUtils.isEmpty(unitWiseServiceList)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(unitWiseServiceList);
    }
}
