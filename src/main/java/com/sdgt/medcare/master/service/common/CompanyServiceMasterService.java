package com.sdgt.medcare.master.service.common;


import com.sdgt.medcare.master.dto.common.CompanyAndServiceRequest;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.CompanyServiceMaster;
import com.sdgt.medcare.master.repository.common.CompanyMasterRepository;
import com.sdgt.medcare.master.repository.common.CompanyServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceMasterService {


   @Autowired
   private CompanyServiceMasterRepository companyServiceMasterRepository;

   @Autowired
   private ServiceMasterRepository serviceMasterRepository;

   @Autowired
   private CompanyMasterRepository companyMasterRepository;

   @Autowired
   private UnitMasterRepository unitMasterRepository;

   /**
    * @param serviceCode
    *
    * @return
    */
   public List<CompanyServiceMaster> getByServiceCode(String serviceCode) {
      ServiceMaster serviceMaster = serviceMasterRepository.findByCodeIgnoreCase(serviceCode);

      List<CompanyServiceMaster> companyServiceMaster = companyServiceMasterRepository.findByServiceMaster(serviceMaster);
      return companyServiceMaster;
   }

   public CompanyServiceMaster getByComAndSer(CompanyAndServiceRequest companyAndServiceRequest, Long unitId) {

      UnitMaster uMaster = unitMasterRepository.getOne(unitId);
      String serviceCode = companyAndServiceRequest.getServiceCode();
      String companyCode = companyAndServiceRequest.getCompanyCode();
      ServiceMaster serviceMaster = serviceMasterRepository.findByCodeIgnoreCase(serviceCode);
      System.out.println(serviceMaster);
      CompanyMaster companyMaster = companyMasterRepository.findByCode(companyCode);
      System.out.println(companyMaster);
      CompanyServiceMaster companyServiceMaster = companyServiceMasterRepository.findByServiceMasterAndCompanyMasterAndUnitMaster(serviceMaster, companyMaster, uMaster);
      return companyServiceMaster;
   }

   //FIXME: dead method
   public List<CompanyServiceMaster> getServiceAndCode(String serviceCode, String code) {
      ServiceMaster serviceMaster = serviceMasterRepository.findByCodeIgnoreCase(serviceCode);
      return null;
   }


}
