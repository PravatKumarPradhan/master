package com.sdgt.medcare.master.service.common;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.core.exceptions.DataException;
import com.core.service.BaseService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.dto.common.ProcedureDTO;
import com.sdgt.medcare.master.entity.global.AnaesthesiaTypeMaster;
import com.sdgt.medcare.master.entity.global.OperationTypeMaster;
import com.sdgt.medcare.master.entity.global.ProcedureMaster;
import com.sdgt.medcare.master.entity.global.ProcedureTypeMaster;
import com.sdgt.medcare.master.entity.global.ResourceDetail;
import com.sdgt.medcare.master.entity.global.TheatreRoomMaster;
import com.sdgt.medcare.master.entity.global.TheatreTypeMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.exeception.ConflictException;
import com.sdgt.medcare.master.repository.common.AnaesthesiaTypeMasterRepository;
import com.sdgt.medcare.master.repository.common.DepartmentMasterRepository;
import com.sdgt.medcare.master.repository.common.OperationTypeMasterRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.ProcedureRepository;
import com.sdgt.medcare.master.repository.common.ProcedureTypeMasterRepository;
import com.sdgt.medcare.master.repository.common.ResourceDetailsRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.TheatreRoomMasterRepository;
import com.sdgt.medcare.master.repository.common.TheatreTypeMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitServiceMapperRepository;
import com.sdgt.medcare.master.util.BaseUtil;
import com.sdgt.medcare.master.util.NumberParseAssistant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProcedureMasterService extends BaseService<ProcedureMaster> {

   /*Type of entity*/
   private final String ID = "id";
   private final String CODE = "code";

   /*Transaction type*/
   private final String CREATE = "create";
   private final String UPDATE = "update";

   final String CHARACTERS_ONLY_REGEX = "[^A-Za-z]+";

   @Autowired
   private ProcedureRepository procedureRepository;

   @Autowired
   private DepartmentMasterRepository departmentMasterRepository;

   @Autowired
   private AnaesthesiaTypeMasterRepository anaesthesiaTypeMasterRepository;

   @Autowired
   private OperationTypeMasterRepository operationTypeMasterRepository;

   @Autowired
   private TheatreRoomMasterRepository theatreRoomMasterRepository;

   @Autowired
   private ServiceMasterRepository serviceMasterRepository;

   @Autowired
   ResourceDetailsRepository resourceDetailsRepository;

   @Autowired
   UnitServiceMapperRepository unitServiceMapperRepository;

   @Autowired
   UnitMasterRepository unitMasterRepository;

   @Autowired
   OrganizationMasterRepository organizationMasterRepository;

   @Autowired
   ProcedureTypeMasterRepository procedureTypeMasterRepository;

   @Autowired
   TheatreTypeMasterRepository theatreTypeMasterRepository;

   /**
    * Constructor.
    *
    * @param procedureRepository object of type {@link ProcedureRepository}
    */
   public ProcedureMasterService(ProcedureRepository procedureRepository) {
      super.setDao(dao);
      this.procedureRepository = procedureRepository;
   }


   @Autowired
   ResourceDeatilService resourceMasterService;

   /**
    * @return {@link ProcedureMaster}
    */
   @Override
   public ProcedureMaster create(ProcedureMaster procedureMaster) {
      ProcedureMaster procedureMasterResponse = null;
      try {
         if (BaseUtil.isNullOrEmptyObject(procedureMaster.getActive())) {
            procedureMaster.setActive(true);
         } else
            procedureMaster.setActive(procedureMaster.getActive());

         procedureMasterResponse = procedureRepository.save(procedureMaster);
         List<ResourceDetail> res = new ArrayList<ResourceDetail>();
         List<ResourceDetail> createResource = null;
         if (procedureMaster.getResourceDetails() != null && !procedureMaster.getResourceDetails().isEmpty()) {
            for (ResourceDetail resource : procedureMaster.getResourceDetails()) {
               ResourceDetail resourceDetail = new ResourceDetail();
               resourceDetail.setResourceType(resource.getResourceType());
               resourceDetail.setResourceName(resource.getResourceName());
               resourceDetail.setNumberOfResource(resource.getNumberOfResource());
               res.add(resourceDetail);
               resourceDetail.setProcedureMaster(procedureMasterResponse);
               createResource = resourceDetailsRepository.saveAll(res);
            }
         }
         procedureMasterResponse.setResourceDetails(createResource);
      } catch (Exception e) {
         new ConflictException("Code Or Description Already  Exist");

      }

      return procedureMasterResponse;
   }


   /**
    * @param pageable
    *
    * @return
    */
   public Page<ProcedureMaster> getAllPaginated(Pageable pageable) {
      Page<ProcedureMaster> procedureMaster = procedureRepository.findAll(pageable);
      return procedureMaster;
   }

   /**
    * @param id
    *
    * @return
    */
   public ProcedureMaster getByProcedureId(Long id) {
      ProcedureMaster procedureMaster = procedureRepository.getOne(id);
      return procedureMaster;
   }

   public ProcedureMaster update(Long id, ProcedureMaster procedureMaster) {
      ProcedureMaster existingProcedureMaster = procedureRepository.getOne(id);

      if (existingProcedureMaster != null) {
         return procedureRepository.save(procedureMaster);
      } else {
         return null;
      }
   }

   /**
    * Save {@link ProcedureMaster}
    *
    * @param dto given
    *
    * @return {@link ProcedureDTO}
    */
   @Transactional
   public ProcedureDTO save(final ProcedureDTO dto) {
      if (dto == null) {
         dto.setErrorMessage("Request is null.");
         return dto;
      }

      dto.verify();
      if (StringUtils.isNotBlank(dto.getErrorMessage())) {
         return dto;
      }

      Date currentDate = Date.from(Instant.now());

      ProcedureMaster procedureMaster;
      if (StringUtils.isBlank(dto.getProcedureId())) {
         dto.setTransactionPerformed(CREATE);
         procedureMaster = new ProcedureMaster();
         if (StringUtils.isNotBlank(dto.getProcedureCode())) {
            procedureMaster.setCode(dto.getProcedureCode());
         }
         if (StringUtils.isNotBlank(dto.getProcedureDesc())) {
            procedureMaster.setDesc(dto.getProcedureDesc());
         }
         procedureMaster.setCreatedBy(dto.getCreatedBy());
         procedureMaster.setCreatedDate(currentDate);

      } else {
         procedureMaster = procedureRepository.getOne(NumberParseAssistant.parseLong(dto.getProcedureId()));

         if (StringUtils.isNotBlank(dto.getProcedureDesc())) {
            procedureMaster.setDesc(dto.getProcedureDesc());
         }

         procedureMaster.setCreatedBy(dto.getCreatedBy());
         procedureMaster.setUpdatedBy(dto.getCreatedBy());
         procedureMaster.setUpdatedDate(currentDate);
         dto.setTransactionPerformed(UPDATE);
      }

		/*if (StringUtils.isBlank(dto.getCptName())) {
			if (StringUtils.isNotBlank(dto.getCptName())) {
				procedureMaster.setCptName(dto.getCptName());
			}
		} else {
			procedureMaster.setCptCode(dto.getCptCode());
		}*/

      if (StringUtils.isNotBlank(dto.getCptName())) {
         procedureMaster.setCptName(dto.getCptName());
      }

      if (StringUtils.isNotBlank(dto.getCptCode())) {
         procedureMaster.setCptCode(dto.getCptCode());
      }

      DepartmentMaster departmentMaster;
      if (checkIfGivenIdOrCode(dto.getDepartmentId(), dto.getDepartmentCode()).equalsIgnoreCase(ID)) {
         departmentMaster = departmentMasterRepository.getOne(NumberParseAssistant.parseLong(dto.getDepartmentId()));
      } else {
         departmentMaster = departmentMasterRepository.findByCode(dto.getDepartmentCode());
      }
      if (procedureMaster != null) {
         procedureMaster.setDepartment(departmentMaster);
      }

      AnaesthesiaTypeMaster anaesthesiaTypeMaster = null;
      if (checkIfGivenIdOrCode(dto.getAnaesthesiaTypeId(), dto.getAnaesthesiaTypeCode()).equalsIgnoreCase(ID)) {
         anaesthesiaTypeMaster = anaesthesiaTypeMasterRepository.getOne(NumberParseAssistant.parseLong(dto.getAnaesthesiaTypeId()));
      } else if (StringUtils.isNotBlank(dto.getAnaesthesiaTypeCode())) {
         anaesthesiaTypeMaster = anaesthesiaTypeMasterRepository.findByCode(dto.getAnaesthesiaTypeCode());
      }
      if (anaesthesiaTypeMaster != null) {
         procedureMaster.setAnaesthesiaTypeMaster(anaesthesiaTypeMaster);
      }

      OperationTypeMaster operationTypeMaster = null;
      if (checkIfGivenIdOrCode(dto.getOperationTypeId(), dto.getOperationTypeCode()).equalsIgnoreCase(ID)) {
         operationTypeMaster = operationTypeMasterRepository.getOne(NumberParseAssistant.parseLong(dto.getOperationTypeId()));
      } else if (StringUtils.isNotBlank(dto.getOperationTypeCode())) {
         operationTypeMaster = operationTypeMasterRepository.findByCode(dto.getOperationTypeCode());
      }
      if (operationTypeMaster != null) {
         procedureMaster.setOperationTypeMaster(operationTypeMaster);
      }

      TheatreRoomMaster operationTheatreRoomMaster = null;
      if (checkIfGivenIdOrCode(dto.getOperationTheatreRoomId(), dto.getOperationTheatreRoomCode()).equalsIgnoreCase(ID)) {
         operationTheatreRoomMaster = theatreRoomMasterRepository.getOne(NumberParseAssistant.parseLong(dto.getOperationTheatreRoomId()));
      } else if (StringUtils.isNotBlank(dto.getOperationTheatreRoomCode())) {
         operationTheatreRoomMaster = theatreRoomMasterRepository.findByCode(dto.getOperationTheatreRoomCode());
      }
      if (operationTheatreRoomMaster != null) {
         procedureMaster.setTheatreRoomMaster(operationTheatreRoomMaster);
      }

      TheatreTypeMaster theatreTypeMaster = null;
      if (checkIfGivenIdOrCode(dto.getTheaterTypeId(), dto.getProcedureTypeCode()).equalsIgnoreCase(ID)) {
         theatreTypeMaster = theatreTypeMasterRepository.getOne(NumberParseAssistant.parseLong(dto.getTheaterTypeId()));
      } else if (StringUtils.isNotBlank(dto.getTheaterTypeCode())) {
         theatreTypeMaster = theatreTypeMasterRepository.findByCode(dto.getTheaterTypeCode());
      }
      if (theatreTypeMaster != null) {
         procedureMaster.setTheatreTypeMaster(theatreTypeMaster);
      }


      if (StringUtils.isNotBlank(dto.getDuration())) {
         procedureMaster.setDuration(StringUtils.getDigits(dto.getDuration()));
         procedureMaster.setDurationUnit(dto.getDuration().replaceAll(CHARACTERS_ONLY_REGEX, ""));
      }

      ServiceMaster serviceMaster;
      if (checkIfGivenIdOrCode(dto.getServiceId(), dto.getServiceCode()).equalsIgnoreCase(ID)) {
         serviceMaster = serviceMasterRepository.getOne(NumberParseAssistant.parseLong(dto.getServiceId()));
      } else {
         serviceMaster = serviceMasterRepository.findByCodeIgnoreCase(dto.getServiceCode());
      }
      if (serviceMaster != null) {
         procedureMaster.setServiceMaster(serviceMaster);
      }

      UnitMaster unitMaster = unitMasterRepository.findByCode(dto.getUnitCode());
      if (unitMaster != null) {
         procedureMaster.setUnitMaster(unitMaster);
      }

      OrganizationMaster organizationMaster = organizationMasterRepository.findByCode(dto.getOrgCode());
      if (organizationMaster != null) {
         procedureMaster.setOrganizationMaster(organizationMaster);
      }

      UnitServiceMapper unitServiceMapper = null;
      if (StringUtils.isNotBlank(dto.getUnitServiceMapperId())) {
         unitServiceMapper = unitServiceMapperRepository.getOne(NumberParseAssistant.parseLong(dto.getUnitServiceMapperId()));
      } else {
         if (serviceMaster != null && unitMaster != null) {
            unitServiceMapper = unitServiceMapperRepository.findByServiceMasterAndUnitMaster(serviceMaster, unitMaster);
         }
      }
      if (unitServiceMapper != null) {
         procedureMaster.setUnitServiceMapper(unitServiceMapper);
      }

      ProcedureTypeMaster procedureTypeMaster = null;
      if (checkIfGivenIdOrCode(dto.getProcedureTypeId(), dto.getProcedureTypeCode()).equalsIgnoreCase(ID)) {
         procedureTypeMaster = procedureTypeMasterRepository.getOne(NumberParseAssistant.parseLong(dto.getProcedureTypeId()));
      } else if (StringUtils.isNotBlank(dto.getProcedureTypeCode())) {
         procedureTypeMaster = procedureTypeMasterRepository.findByCode(dto.getProcedureTypeCode());
      }
      if (procedureTypeMaster != null) {
         procedureMaster.setProcedureTypeMaster(procedureTypeMaster);
      }

      procedureMaster = procedureRepository.save(procedureMaster);

      dto.setProcedureId(procedureMaster.getId());
      dto.setTransactionSuccess(true);
      return dto;

   }

   /**
    * Identifies if given is id or code.
    *
    * @param id   given valid id.
    * @param code given valid code.
    *
    * @return key code if id is blank or otherwise.
    */
   private String checkIfGivenIdOrCode(String id, String code) {
      if (StringUtils.isBlank(id) && StringUtils.isBlank(code)) {
         return "";
      }

      if (StringUtils.isBlank(id)) {
         return CODE;
      } else {
         return ID;
      }
   }

   /**
    *
    * @param id
    * @param pathcObject
    * @return
    */
   public ProcedureMaster deActivateProcedure(Long id, String pathcObject) {
      Optional<ProcedureMaster> procedureMaster = procedureRepository.findById(id);
      JsonObject obj = new JsonParser().parse(pathcObject).getAsJsonObject();
      ProcedureMaster details = new ProcedureMaster();
      if (procedureMaster.isPresent()) {
         details = procedureMaster.get();
         String deletedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
         details.setUpdatedBy(deletedUserName);
         details.setActive(obj.get("active").getAsBoolean());
           /* if(obj.get("remarks").)
            details.setRemarks(obj.get("remarks").getAsString());*/
      }
      return patch(details, id.toString());
   }

   @Override
   public ProcedureMaster patch(ProcedureMaster objectTopatch, String id) {
      if (objectTopatch == null)
         throw new DataException("There should exist an object already");
      if (StringUtils.isNotBlank(id)) {
         try {
            objectTopatch.setId(id);
            return procedureRepository.save(objectTopatch);
         } catch (Exception e) {
            throw new DataException("persistance error from save", e);
         }
      }
      return null;
   }


   /**
    *
    * @param unitServiceMapperId
    * @return
    */
  public   List<ProcedureMaster> findByUnitService(final Long unitServiceMapperId){
      UnitServiceMapper unitServiceMapper=unitServiceMapperRepository.getOne(unitServiceMapperId) ;
      List  <ProcedureMaster> procedureMaster=procedureRepository.findByUnitServiceMapper(unitServiceMapper);
      return procedureMaster;
   }






}
