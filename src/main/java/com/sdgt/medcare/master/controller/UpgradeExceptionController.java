package com.sdgt.medcare.master.controller;


import com.core.base.rest.controller.RestWSController;
import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.dto.UpgradeExceptionDTO;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import com.sdgt.medcare.master.service.UpgradeExceptionService;
import com.sdgt.medcare.master.service.common.FinancialClassMasterService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/upgradeException")
@RestController
public class UpgradeExceptionController extends RestWSController<UpgradeExceptionController> {

    private Logger logger = LoggerFactory.getLogger(UpgradeExceptionController.class);

    @Autowired
    UpgradeExceptionService upgradeExceptionService;

    @Autowired
    FinancialClassMasterService financialClassMasterService;





    @GetMapping("/findUpgradeException")
    public ResponseEntity<UpgradeExceptionDTO> findUpgradeException(final @RequestParam(value = "subGroupMasterId", required = false) String subGroupMasterId,
                                                                           final @RequestParam(value = "upgradeType") String upgradeType)
    {

        try {


            final String unitId = HttpUtils.getHeader(HttpHeaders.UNIT_ID);
            final String orgId = HttpUtils.getHeader(HttpHeaders.ORG_ID);

                return new ResponseEntity<UpgradeExceptionDTO>(upgradeExceptionService.checkUpgradeExceptionFinancialClassExist(subGroupMasterId,upgradeType,unitId,orgId), HttpStatus.OK);

        }
        catch (Exception exception) {
            logger.error(ExceptionUtils.getStackTrace(exception));
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping(path = "/findFinancialClassHierarchy/{financial_class_code}")
    public @ResponseBody
    ResponseEntity<Object> findFinancialClassHierarchy(@PathVariable String financial_class_code)
    {

        try {
                return new ResponseEntity<>(financialClassMasterService.findByCode(financial_class_code), HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error(ExceptionUtils.getStackTrace(exception));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error For Update Write Off Details");
        }

    }


}
