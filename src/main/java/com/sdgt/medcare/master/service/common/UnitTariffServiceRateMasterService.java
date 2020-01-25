package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.dto.billing.ServiceRateRequest;
import com.sdgt.medcare.master.dto.common.StatusEnum;
import com.sdgt.medcare.master.dto.common.StatusResponse;
import com.sdgt.medcare.master.dto.common.UnitServiceTariffDTO;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitTariffServiceRateMasterRepository;
import com.sdgt.medcare.master.util.NumberParseAssistant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Service
public class UnitTariffServiceRateMasterService extends BaseService<UnitTariffServiceRateMaster> {
   @Autowired
   private UnitTariffServiceRateMasterRepository unitTariffServiceRateMasterRepository;
   @Autowired
   private FinancialClassMasterService financialClassMasterService;
   @Autowired
   private TariffMasterService tariffMasterService;
   @Autowired
   private VisitTypeMasterService visitTypeMasterService;
   @Autowired
   private UnitServiceMapperService unitServiceMapperService;
   @Autowired
   private UnitMasterService unitMasterService;
   @Autowired
   private ServiceMasterRepository serviceMasterRepository;
    @Autowired
    private OrganizationMasterRepository organizationMasterRepository;


   @Autowired
   private UnitMasterRepository unitMasterRepository;

   /**
    * Returns {@link UnitTariffServiceRateMaster} by given request.
    *
    * @param financialClassCode a valid code from {@link com.sdgt.medcare.master.entity.org.FinancialClassMaster}
    * @param tariffCode         a valid code from {@link com.sdgt.medcare.master.entity.org.TariffMaster}
    * @param visitCode          a valid code from {@link com.sdgt.medcare.master.entity.global.VisitTypeMaster}
    * @param unitServiceMapper  a valid code from {@link UnitServiceMapper}
    *
    * @return the {@link UnitTariffServiceRateMaster} object for the given.
    */
   public List<UnitTariffServiceRateMaster> findByRequest(final String financialClassCode, final String tariffCode, final String visitCode, final UnitServiceMapper unitServiceMapper) {
      final FinancialClassMaster financialClassMaster = financialClassMasterService.findByCode(financialClassCode);
      final TariffMaster tariffMaster = tariffMasterService.findByCode(tariffCode);
      final VisitTypeMaster visitTypeMaster = visitTypeMasterService.findByCode(visitCode);

      //FIXME: We need to use Query by example. But this is taking more time because of Hong Kong delivery
        /*final UnitTariffServiceRateMaster unitTariffServiceRateMaster = new UnitTariffServiceRateMaster();
        unitTariffServiceRateMaster.setFinancialClassMaster(financialClassMaster);
        unitTariffServiceRateMaster.setTariffMaster(tariffMaster);
        unitTariffServiceRateMaster.setVisitTypeMaster(visitTypeMaster);
        unitTariffServiceRateMaster.setUnitServiceMapper(unitServiceMapper);


        Example<UnitTariffServiceRateMaster> example = Example.of(unitTariffServiceRateMaster);
        return unitTariffServiceRateMasterRepository.findAll(example);*/

      if (financialClassMaster == null || visitTypeMaster == null || tariffMaster == null) {
         return unitTariffServiceRateMasterRepository.findAllByUnitServiceMapperAndActive(unitServiceMapper, true);
      } else {
         return unitTariffServiceRateMasterRepository.
               findAllByFinancialClassMasterAndVisitTypeMasterAndTariffMasterAndUnitServiceMapperAndActive(financialClassMaster, visitTypeMaster, tariffMaster, unitServiceMapper, true);
      }
   }

   /**
    * Returns {@link UnitTariffServiceRateMaster} by given request.
    *
    * @param request a valid code from {@link ServiceRateRequest}
    *
    * @return the {@link UnitTariffServiceRateMaster} object for the given.
    */
   public List<UnitTariffServiceRateMaster> findByRequest(final ServiceRateRequest request) {
      final FinancialClassMaster financialClassMaster = Objects.requireNonNull(financialClassMasterService.findByCode(request.getFinancialClassCode()),
            "financialClassCode not valid.");
      final TariffMaster tariffMaster = Objects.requireNonNull(tariffMasterService.findByCode(request.getTariffCode()),
            "tariffCode not valid");
      final VisitTypeMaster visitTypeMaster = Objects.requireNonNull(visitTypeMasterService.findByCode(request.getVisitTypeCode()),
            "visitTypeCode not valid");
      final UnitMaster unitMaster = Objects.requireNonNull(unitMasterService.findByCode(request.getUnitCode()),
            "unitServiceMapperId not valid");

      return unitTariffServiceRateMasterRepository.findAllByFinancialClassMasterAndVisitTypeMasterAndTariffMasterAndUnitMasterAndActive(financialClassMaster,
            visitTypeMaster, tariffMaster, unitMaster, true);
   }


   public Page<UnitTariffServiceRateMaster> getAllTariffService(Pageable pageable) {
      return unitTariffServiceRateMasterRepository.findAll(pageable);
   }

   public Page<UnitTariffServiceRateMaster> getTariffServiceByUnitMaster(Long id, Pageable pageable) {
      UnitMaster unitMaster = unitMasterRepository.getOne(id);
      return unitTariffServiceRateMasterRepository.findAllByUnitMaster(unitMaster, pageable);
   }


   @Transactional
   public List<UnitTariffServiceRateMaster> create(Collection<UnitServiceTariffDTO> dtoCollection) {
      if (CollectionUtils.isEmpty(dtoCollection)) {
         //FIXME return appropriate response
         return Collections.emptyList();
      }

      final List<UnitTariffServiceRateMaster> unitTariffServiceRateMasterCollection = new ArrayList<>(dtoCollection.size());

      for(final UnitServiceTariffDTO dto: dtoCollection) {
         final TariffMaster tariffMaster = tariffMasterService.findByCode(dto.getTariffCode());
         final FinancialClassMaster financialClassMaster = financialClassMasterService.findByCode(dto.getFinancialClassCode());
         final VisitTypeMaster visitTypeMaster = visitTypeMasterService.findByCode(dto.getVisitTypeCode());
         final UnitServiceMapper unitServiceMapper = unitServiceMapperService.findById(dto.getUnitServiceMapperId());
         final OrganizationMaster organizationMaster= organizationMasterRepository.findByCode(dto.getOrgCode());
         final UnitMaster unitMaster=unitMasterService.findByCode(dto.getUnitCode());


         if (StringUtils.isBlank(dto.getUnitTariffServiceRateId())) {
            /*Create*/
            final UnitTariffServiceRateMaster unitTariffServiceRateMaster = new UnitTariffServiceRateMaster();
            unitTariffServiceRateMaster.setTariffMaster(tariffMaster);
            unitTariffServiceRateMaster.setVisitTypeMaster(visitTypeMaster);
            unitTariffServiceRateMaster.setFinancialClassMaster(financialClassMaster);
            unitTariffServiceRateMaster.setUnitServiceMapper(unitServiceMapper);
            unitTariffServiceRateMaster.setRate(dto.getRate().floatValue());
            unitTariffServiceRateMaster.setOrganizationMaster(organizationMaster);
            unitTariffServiceRateMaster.setUnitMaster(unitMaster);
            unitTariffServiceRateMaster.setEffectiveDate(dto.getEffectiveDate());
            if(dto.getActive()==null) {
               unitTariffServiceRateMaster.setActive(true);
            }
            unitTariffServiceRateMaster.setCreatedBy(dto.getCreatedBy());
            //TODO bind data

            unitTariffServiceRateMasterCollection.add(unitTariffServiceRateMaster);
         } else {
            /*Update*/
            final Optional<UnitTariffServiceRateMaster> unitTariffServiceRateMasterOptional = unitTariffServiceRateMasterRepository.findById(NumberParseAssistant.parseLong(dto.getUnitTariffServiceRateId()));
            final UnitTariffServiceRateMaster existingUnitTariffServiceRateMaster = unitTariffServiceRateMasterOptional.orElse(null);
            //TODO update data in existing master data

            unitTariffServiceRateMasterCollection.add(existingUnitTariffServiceRateMaster);
         }
      }
      final List<UnitTariffServiceRateMaster> savedMasterList = unitTariffServiceRateMasterRepository.saveAll(unitTariffServiceRateMasterCollection);

      return savedMasterList;
   }

   /**
    * Inactivate/Activate {@link UnitTariffServiceRateMaster}
    *
    * @param unitTariffServiceRateMasterId given valid Id.
    * @param newStatus valid status of type {@link StatusEnum}
    * @return {@link StatusResponse} object.
    */
   public StatusResponse updateStatus(String unitTariffServiceRateMasterId, StatusEnum newStatus) {
      String entity = "UnitTariffServiceRateMaster";
      if (StringUtils.isBlank(unitTariffServiceRateMasterId)) {
         StatusResponse statusResponse = new StatusResponse();
         statusResponse.setEntity(entity);
         statusResponse.setErrorMessage("update;UnitTariffServiceRateMaster; Id is null.");

         return statusResponse;
      }

      Optional<UnitTariffServiceRateMaster> unitTariffServiceRateMasterOptional = unitTariffServiceRateMasterRepository
              .findById(NumberParseAssistant.parseLong(unitTariffServiceRateMasterId));

      if (!unitTariffServiceRateMasterOptional.isPresent()) {
         StatusResponse statusResponse = new StatusResponse();
         statusResponse.setEntity(entity);
         statusResponse.setStatus(newStatus);
         statusResponse.setId(unitTariffServiceRateMasterId);
         statusResponse.setErrorMessage("update;UnitTariffServiceRateMaster; Id not found.");

         return statusResponse;
      }

      UnitTariffServiceRateMaster existingUnitTariffServiceRateMaster = unitTariffServiceRateMasterOptional.get();

      StatusEnum currentStatus = existingUnitTariffServiceRateMaster.getActive() ? StatusEnum.ACTIVE : StatusEnum.IN_ACTIVE;
      if (currentStatus.equals(newStatus)) {
         StatusResponse statusResponse = new StatusResponse();
         statusResponse.setEntity(entity);
         statusResponse.setStatus(newStatus);
         statusResponse.setId(unitTariffServiceRateMasterId);
         statusResponse.setErrorMessage("update;UnitTariffServiceRateMaster; Status already set to that of requested.");

         return statusResponse;
      }

      existingUnitTariffServiceRateMaster.setActive(newStatus.equals(StatusEnum.ACTIVE));

      unitTariffServiceRateMasterRepository.save(existingUnitTariffServiceRateMaster);

      StatusResponse statusResponse = new StatusResponse();
      statusResponse.setEntity(entity);
      statusResponse.setStatus(newStatus);
      statusResponse.setId(unitTariffServiceRateMasterId);
      statusResponse.setExecutionComplete(true);

      return statusResponse;
   }


}
