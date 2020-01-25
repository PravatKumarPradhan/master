package com.sdgt.medcare.master.controller.common;


import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.dto.common.CompanyAndServiceRequest;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.CompanyServiceMaster;
import com.sdgt.medcare.master.repository.common.UnitRepository;
import com.sdgt.medcare.master.service.common.CompanyServiceMasterService;
import com.sdgt.medcare.master.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("company-service")
public class CompanyServiceMasterController {

   @Autowired
   CompanyServiceMasterService companyServiceMasterService;
   @Autowired
   private UnitRepository unitRepository;


   @GetMapping ("/getByServiceCode/{code}")
   public List<CompanyServiceMaster> getDate(@PathVariable String code) {
      return companyServiceMasterService.getByServiceCode(code);
   }

   @PostMapping ("/getByServiceAndCompany")
   public List<CompanyServiceMaster> getDataByServiceAndCompany(@RequestBody CompanyAndServiceRequest companyAndServiceRequest) {
      String unitId = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
      String uCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
      if (uCode != null) {
         List<UnitMaster> uMaster = unitRepository.findByCode(uCode);
         Long uId = BaseUtil.getUnitId(uMaster.stream().filter(unitMasterCode -> unitMasterCode.getCode().equalsIgnoreCase(uCode)).findFirst());
         return Collections.singletonList(companyServiceMasterService.getByComAndSer(companyAndServiceRequest,uId));
      }
        else {
         return Collections.emptyList();

      }

   }
}
