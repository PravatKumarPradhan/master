package com.sdgt.medcare.master.service.common;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.core.exceptions.DataException;
import com.core.model.BaseEntity;
import com.core.service.BaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.async.ElasticSearchAssistant;
import com.sdgt.medcare.master.dto.common.ServiceViewMapperDTO;
import com.sdgt.medcare.master.dto.common.UnitServiceMapperDTO;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.DependentUnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.repository.common.DepenedentUnitServiceMapperRepository;
import com.sdgt.medcare.master.repository.common.GroupMasterRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceCategoryMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.SubGroupMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitServiceMapperRepository;
import com.sdgt.medcare.master.repository.common.VisitTypeMasterRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 * @author Pravat Kumar Pradhan
 */
@Service
public class UnitServiceMapperService extends BaseService<UnitServiceMapper> {
   @Autowired
   private UnitServiceMapperRepository unitServiceMapperRepository;
   @Autowired
   private UnitMasterRepository unitMasterRepository;
   @Autowired
   private ServiceMasterRepository serviceMasterRepository;

   @Autowired
   private OrganizationMasterRepository organizationMasterRepository;

   @Autowired
   private GroupMasterRepository groupMasterRepository;

   @Autowired
   private SubGroupMasterRepository subGroupMasterRepository;

   @Autowired
   private ServiceCategoryMasterRepository serviceCategoryMasterRepository;

   @Autowired
   private DepenedentUnitServiceMapperRepository depenedentUnitServiceMapperRepository;

   @Autowired
   private VisitTypeMasterRepository visitTypeMasterRepository;

   @Autowired
   private ElasticSearchAssistant elasticSearchAssistant;

   private ServiceViewMapperDTO serviceViewMapperDTO;

   Logger unitServiceMapperLogger = LoggerFactory.getLogger(UnitServiceMapper.class);
   ServiceMaster newService = new ServiceMaster();

   /**
    * Parametrized constructor
    *
    * @param unitServiceMapperRepository
    */
   public UnitServiceMapperService(final UnitServiceMapperRepository unitServiceMapperRepository) {
      super.setDao(unitServiceMapperRepository);
   }

   @Value ("${paging.default-size}")
   private int searchListSize;

   /**
    * Finds all {@link UnitServiceMapper} by given {@link UnitMaster}.
    *
    * @param unitMaster a valid unit in the {@link UnitMaster}
    * @param pageSize   page size.
    * @param subString  a valid substring.
    *
    * @return an object of type {@link UnitMaster}. Else null if not found.
    */
   public List<UnitServiceMapper> findAllByUnitMaster(final UnitMaster unitMaster, final String subString, final Integer pageSize) {
      final Pageable searchBestTenMatch = (pageSize != null) ? PageRequest.of(0, pageSize) : PageRequest.of(0, searchListSize);
      final String searchKey = ("%".equalsIgnoreCase(subString) ? subString : "%" + subString + "%");
      return unitServiceMapperRepository.findAllByUnitMaster(unitMaster, searchKey, searchBestTenMatch);
   }

   /**
    * Gets all procedure services.
    *
    * @param unitMaster a vaild code of type {@link UnitMaster}
    *
    * @return lsit of {@link UnitServiceMapper}
    */
   public List<UnitServiceMapper> findAllProcedureService(final UnitMaster unitMaster) {
      return unitServiceMapperRepository.findAllProcedureService(unitMaster);
   }

   /**
    * Gets {@link UnitServiceMapper} object by given code.
    *
    * @param unitServiceMapperCode a valid code from {@link UnitServiceMapper}
    *
    * @return the {@link UnitServiceMapper}
    */
   public UnitServiceMapper findByCode(final String unitServiceMapperCode) {
      return unitServiceMapperRepository.findByCode(unitServiceMapperCode);
   }

   /**
    * Gets {@link UnitServiceMapper} object by given id.
    *
    * @param unitServiceMapperId a valid code from {@link UnitServiceMapper}
    *
    * @return the {@link UnitServiceMapper}
    */
   public UnitServiceMapper findById(final Long unitServiceMapperId) {
      Optional<UnitServiceMapper> unitServiceMapperOptional = unitServiceMapperRepository.findById(unitServiceMapperId);

      return unitServiceMapperOptional.orElse(null);
   }

   public UnitServiceMapper getUnitServiceMapper(final String unitCode, final String serviceCode) {
      final UnitMaster unitMaster = unitMasterRepository.findByCode(unitCode);
      final ServiceMaster serviceMaster = serviceMasterRepository.findByCodeIgnoreCase(serviceCode);
      return unitServiceMapperRepository.findByUnitMasterAndServiceMaster(unitMaster, serviceMaster);
   }

   public List<UnitServiceMapper> create(List<UnitServiceMapper> list) {

      List<UnitServiceMapper> my = new ArrayList<>();
      Iterator itr = list.iterator();

      List<UnitServiceMapper> list1 = (List<UnitServiceMapper>) dao.saveAll(list);
      while (itr.hasNext()) {
         UnitServiceMapper ump = (UnitServiceMapper) itr.next();
         this.validateEntity(ump);
         if (ump instanceof BaseEntity) {

            BaseEntity baseEntity = ump;
            if (baseEntity.getId() != null) {
               throw new DataException("Creation object's payload should not contain id   entity error this is save ");
            }
            my.add(this.dao.save(ump));
         }
      }
      return list;
   }

   /**
    * @param mapperDTO
    *
    * @return
    */
   @Transactional
   public List<UnitServiceMapper> createUnitServiceDate(UnitServiceMapperDTO mapperDTO) {
      List<UnitServiceMapper> list = new ArrayList<UnitServiceMapper>();
      UnitServiceMapper unitServiceMapper = null;

      if (mapperDTO.getServiceMaster() != null) {
         String userName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
         ServiceMaster serviceMaster = mapperDTO.getServiceMaster();
         serviceMaster.setCreatedBy(userName);
         Long orgId = Long.parseLong(mapperDTO.getServiceMaster().getOrganizationMaster().getId());
         Long subGroupId = Long.parseLong(mapperDTO.getServiceMaster().getSubGroupMaster().getId());
         Long groupId = Long.parseLong(mapperDTO.getServiceMaster().getGroupMaster().getId());
         Optional<OrganizationMaster> organizationMaster = organizationMasterRepository.findById(orgId);
         Optional<SubGroupMaster> subGroupMaster = subGroupMasterRepository.findById(subGroupId);
         Optional<GroupMaster> groupMaster = groupMasterRepository.findById(groupId);
         //   serviceMaster.setGroupMaster(groupoupMaster.filter(id->id.getId().equalsIgnoreCase(groupId)));
         serviceMaster.setGroupMaster(groupMaster.get());
         serviceMaster.setOrganizationMaster(organizationMaster.get());
         //  serviceMaster.setOrganizationMaster(mapperDTO.getOrganizationMaster());
         serviceMaster.setSubGroupMaster(subGroupMaster.get());
         serviceMaster.setServiceCodificationCode(mapperDTO.getServiceMaster().getServiceCodificationCode());
         serviceMaster.setDesc(mapperDTO.getServiceMaster().getDesc());
         /*if(mapperDTO.getServiceMaster().getValidityTo()!=null|| StringUtils.isNotBlank(mapperDTO.getServiceMaster().getValidityTo().toString())){
            serviceMaster.setValidityTo(mapperDTO.getServiceMaster().getValidityTo());
         }

         if(mapperDTO.getServiceMaster().getValidityFrom()!=null || StringUtils.isNotBlank(mapperDTO.getServiceMaster().getValidityFrom().toString())){
            serviceMaster.setValidityFrom(mapperDTO.getServiceMaster().getValidityFrom());
         }*/

         if(mapperDTO.getServiceMaster().getValidityFrom()!=null){
            serviceMaster.setValidityFrom(mapperDTO.getServiceMaster().getValidityFrom());
         }


         if(mapperDTO.getServiceMaster().getValidityTo()!=null){
            serviceMaster.setValidityTo(mapperDTO.getServiceMaster().getValidityTo());
         }

         /*if(StringUtils.isNotBlank(mapperDTO.getServiceMaster().getValidityTo().toString()))
         {
            serviceMaster.setValidityTo(null);
         }*/


         serviceMaster = serviceMasterRepository.save(serviceMaster);
         if (mapperDTO.getServiceMaster().getCode() == null) {
            serviceMaster.setCode(serviceMaster.getId());
         } else {
            serviceMaster.setCode(mapperDTO.getServiceMaster().getCode());
         }
         if (mapperDTO.getServiceMaster().getActive() != null)
            serviceMaster.setActive(mapperDTO.getServiceMaster().getActive());
         else
            serviceMaster.setActive(true);

         newService = serviceMasterRepository.save(serviceMaster);
      }
      try {
         elasticSearchAssistant.syncWithElasticServer(newService,"ServiceMaster",false,false);
      } catch (JsonProcessingException e) {
         unitServiceMapperLogger.error("Error in service master creation in elastic"+e.getMessage());
      }
      if (mapperDTO.getUnitServiceMapper() != null && !mapperDTO.getUnitServiceMapper().isEmpty()) {
         for (UnitServiceMapper unitMapper : mapperDTO.getUnitServiceMapper()) {
            //  UnitServiceMapper unitServiceMapper=null;
            if (unitMapper.getActive() == null)
               unitMapper.setActive(true);
            else
            unitMapper.setActive(unitMapper.getActive());
            unitMapper.setIsAllowMultipleQuantity(unitMapper.getIsAllowMultipleQuantity());
            unitMapper.setIsDoctorRequired(unitMapper.getIsDoctorRequired());
            unitMapper.setServiceMaster(newService);
            Long unitId = Long.parseLong(unitMapper.getUnitMaster().getId());
            Optional<UnitMaster> unitMaster = unitMasterRepository.findById(unitId);
            unitMapper.setUnitMaster(unitMaster.get());
            unitServiceMapper = unitServiceMapperRepository.save(unitMapper);
            if (unitServiceMapper != null) {
               list.add(unitServiceMapper);
            }
            try {
               elasticSearchAssistant.syncWithElasticServer(unitServiceMapper,"UnitServiceMapper",false,false);
            } catch (JsonProcessingException e) {
               unitServiceMapperLogger.error("Error in unitServiceMapper creation in elastic"+e.getMessage());
            }
         }
      }
      return list;
   }

   @Transactional
   public void addUnitService(UnitServiceMapper unitServiceMapper1) {
      UnitServiceMapper uMapper = new UnitServiceMapper();
      uMapper.setEffectiveEndDate(unitServiceMapper1.getEffectiveEndDate());
      uMapper.setEffectiveStartDate(unitServiceMapper1.getEffectiveStartDate());
      if (unitServiceMapper1.getActive() != null) {
         uMapper.setActive(true);
      } else {
         uMapper.setActive(unitServiceMapper1.getActive());
      }
      Long unitId = Long.parseLong(unitServiceMapper1.getUnitMaster().getId());
      Optional<UnitMaster> unitMaster = unitMasterRepository.findById(unitId);
      uMapper.setUnitMaster(unitMaster.get());

   }

   /**
    * @param pageable data based on pageable
    *
    * @return list of UnitService
    */
   public Page<UnitServiceMapper> getAllUnitService(Pageable pageable) {
      List<UnitServiceMapper> posts = Arrays.asList();
      Map<ServiceMaster, List<UnitServiceMapper>> serviceMasterListMap = unitServiceMapperRepository.findAll().parallelStream().filter(unitServiceMapper -> {

         return unitServiceMapper.getServiceMaster() != null && unitServiceMapper.getUnitMaster() != null;

      }).collect(Collectors.groupingBy(uService -> uService.getServiceMaster()));
      unitServiceMapperLogger.info(serviceMasterListMap.toString());
      return unitServiceMapperRepository.findAll(pageable);
   }

   public UnitServiceMapper deActiveUnitService(Long unitServiceId, String statusToUpdate) {
      Optional<UnitServiceMapper> unitServiceMapperOptional = unitServiceMapperRepository.findById(unitServiceId);
      UnitServiceMapper unitServiceMapper = new UnitServiceMapper();
      JsonObject jsonObject = new JsonParser().parse(statusToUpdate).getAsJsonObject();
      String deletedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
      if (unitServiceMapperOptional.isPresent()) {
         unitServiceMapper = unitServiceMapperOptional.get();
         unitServiceMapper.setActive(jsonObject.get("active").getAsBoolean());
         unitServiceMapper.setUpdatedBy(deletedUserName);
      }
      return patch(unitServiceMapper, unitServiceId.toString());
   }

   @Override
   public UnitServiceMapper patch(UnitServiceMapper patchdata, String id) {
      if (patchdata == null) {
         throw new DataException("Data is empty");
      }
      if (StringUtils.isNotBlank(id)) {
         try {
            patchdata.setId(id);
            return unitServiceMapperRepository.save(patchdata);
         } catch (Exception e) {
            throw new DataException("persistance error from save", e);
         }
      }
      return null;
   }


   public List<UnitServiceMapper> findByService(Long service) {
      ServiceMaster serviceMaster = serviceMasterRepository.getOne(service);
      return unitServiceMapperRepository.findByServiceMasterAndActive(serviceMaster,true);
      //  return unitServiceMapperRepository.findByServiceMaster(service);
   }


   public Page<ServiceMaster> getServiceData(Pageable pageable) {
      return serviceMasterRepository.findAll(pageable);
   }


   /**
    * @param id
    * @param pageable
    *
    * @return
    */
   public Page<UnitServiceMapper> getDataByUnitId(Long id, Pageable pageable) {
      UnitMaster unitMaster1 = unitMasterRepository.getOne(id);
      return unitServiceMapperRepository.findAllByUnitMaster(unitMaster1, pageable);
   }


   /**
    * @param id
    *
    * @return the list of service data based unit
    */
   public List<UnitServiceMapper> getListByUnit(Long id) {
      UnitMaster uMaster = unitMasterRepository.getOne(id);
      return unitServiceMapperRepository.findByUnitMaster(uMaster);
   }


   /**
    * @param unitServiceMapper
    * @param id
    *
    * @return
    */
   @Override
   @Transactional
   public UnitServiceMapper update(UnitServiceMapper unitServiceMapper, String id) {

      List<DependentUnitServiceMapper> dependentUnitServiceMappers = new ArrayList<>();
      Long unitServiceId = Long.parseLong(id);
      String orgCode = HttpUtils.getHeader(HttpHeaders.ORG_CODE);
      OrganizationMaster oMaster = organizationMasterRepository.findByCode(orgCode);
      Long orgId = Long.parseLong(oMaster.getId());
      String createdByUpdatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
      UnitServiceMapper unitServiceMapper1 = new UnitServiceMapper();
      UnitServiceMapper uServiceMapper = unitServiceMapperRepository.getOne(unitServiceId);
      UnitMaster unitMaster = unitMasterRepository.getOne(Long.parseLong(unitServiceMapper.getUnitMaster().getId()));
      ServiceMaster sMaster = serviceMasterRepository.getOne(Long.parseLong(unitServiceMapper.getServiceMaster().getId()));
      //   OrganizationMaster organizationMaster = organizationMasterRepository.getOne(Long.parseLong(unitServiceMapper.getOrganizationMaster().getId()));
      OrganizationMaster organizationMaster = organizationMasterRepository.getOne(orgId);

      VisitTypeMaster vTypeMaster = null;
      unitServiceMapper.setId(unitServiceId);
      if (CollectionUtils.isNotEmpty(unitServiceMapper.getVisitTypeMasterList())) {
         List<VisitTypeMaster> visitTypeMasterList = unitServiceMapper.getVisitTypeMasterList();
         visitTypeMasterList.stream().forEach(visitTypeM -> {
            visitTypeM.setId(visitTypeM.getId());
            visitTypeM.setActive(visitTypeM.getActive());
            visitTypeM.setCode(BaseMaster.getCode(visitTypeM));
            visitTypeM.setDesc(BaseMaster.getDesc(visitTypeM));
         });
      }

      /**
       *  for dependedUnitService save
       */
      if (CollectionUtils.isNotEmpty(unitServiceMapper.getDependentUnitServiceMapper())) {
         dependentUnitServiceMappers = unitServiceMapper.getDependentUnitServiceMapper();
         final List<DependentUnitServiceMapper> uu = unitServiceMapper.getDependentUnitServiceMapper();
         dependentUnitServiceMappers.stream().forEach(list -> {
            if (uu.stream().filter(DependentUnitServiceMapper::getActive) == null)
               list.setActive(true);
            list.setFlatServiceRate(list.getFlatServiceRate());
            list.setParentUnitServiceMapper(unitServiceMapper);
            list.setUnitMaster(unitServiceMapper.getUnitMaster());
            list.setOverrideConfiguredRate(list.getOverrideConfiguredRate());
            list.setChildService(list.getChildService());
            if (list.getOverrideConfiguredRate() == true) {
               if (list.getFlatServiceRate() != 0.0 && list.getPercentageOnParentServiceRate() == 0.0) {
                  list.setFlatServiceRate(list.getFlatServiceRate());
                  list.setPercentageOnParentServiceRate(0.0);
               } else if (list.getFlatServiceRate() == 0.0 && list.getPercentageOnParentServiceRate() != 0.0) {
                  list.setFlatServiceRate(0.0);
                  list.setPercentageOnParentServiceRate(list.getPercentageOnParentServiceRate());
               }
            }

         });
      }
      List<DependentUnitServiceMapper> depUnitService = depenedentUnitServiceMapperRepository.saveAll(dependentUnitServiceMappers);

      unitServiceMapper.setDependentUnitServiceMapper(depUnitService);
      unitServiceMapper.setUpdatedBy(createdByUpdatedBy);
      unitServiceMapper.setOrganizationMaster(organizationMaster);
      unitServiceMapper.setUnitMaster(unitMaster);
      unitServiceMapper.setServiceMaster(sMaster);
      return unitServiceMapperRepository.save(unitServiceMapper);

   }


   /**
    * @param unitIdFromCode
    *
    * @return
    */
   public Long getUnitId(Optional<UnitMaster> unitIdFromCode) {
      Long unitId = 0L;
      if (unitIdFromCode.isPresent()) {
         UnitMaster unit = unitIdFromCode.get();
         unitId = Long.parseLong(unit.getId());
      }
      return unitId;
   }


   @Transactional
   public List<UnitServiceMapper> updateService(Long ServiceId, UnitServiceMapperDTO unitServiceMapperDTO) {
      List<UnitServiceMapper> list = new ArrayList<UnitServiceMapper>();

      ServiceMaster serviceMaster = serviceMasterRepository.getOne(ServiceId);
      UnitServiceMapper unitServiceMapper = null;

      /*List<UnitServiceMapper> unitServiceMapperFront = unitServiceMapperDTO.getUnitServiceMapper();



      List<UnitServiceMapper> unitServiceMapperBack = unitServiceMapperRepository.findByServiceMaster(serviceMaster);*/

      serviceMaster.setId(serviceMaster.getId());
      Long orgId = Long.parseLong(unitServiceMapperDTO.getServiceMaster().getOrganizationMaster().getId());
      Long subGroupId = Long.parseLong(unitServiceMapperDTO.getServiceMaster().getSubGroupMaster().getId());
      Long groupId = Long.parseLong(unitServiceMapperDTO.getServiceMaster().getGroupMaster().getId());
      Optional<OrganizationMaster> organizationMaster = organizationMasterRepository.findById(orgId);
      Optional<SubGroupMaster> subGroupMaster = subGroupMasterRepository.findById(subGroupId);
      Optional<GroupMaster> groupMaster = groupMasterRepository.findById(groupId);
      serviceMaster.setGroupMaster(groupMaster.get());
      serviceMaster.setOrganizationMaster(organizationMaster.get());
      serviceMaster.setSubGroupMaster(subGroupMaster.get());
      serviceMaster.setDesc(unitServiceMapperDTO.getServiceMaster().getDesc());
      serviceMaster.setActive(true);

      if(unitServiceMapperDTO.getServiceMaster().getValidityFrom()!=null){
         serviceMaster.setValidityFrom(unitServiceMapperDTO.getServiceMaster().getValidityFrom());
      }


      if(unitServiceMapperDTO.getServiceMaster().getValidityTo()!=null){
         serviceMaster.setValidityTo(unitServiceMapperDTO.getServiceMaster().getValidityTo());
      }

      /*if(unitServiceMapperDTO.getServiceMaster().getValidityTo()!=null|| StringUtils.isNotBlank(unitServiceMapperDTO.getServiceMaster().getValidityTo().toString())){
         serviceMaster.setValidityTo(unitServiceMapperDTO.getServiceMaster().getValidityTo());
      }

      if(unitServiceMapperDTO.getServiceMaster().getValidityFrom()!=null || StringUtils.isNotBlank(unitServiceMapperDTO.getServiceMaster().getValidityFrom().toString())){
         serviceMaster.setValidityFrom(unitServiceMapperDTO.getServiceMaster().getValidityFrom());
      }*/

      ServiceMaster serviceMaster1 = serviceMasterRepository.save(serviceMaster);
      try {
         elasticSearchAssistant.syncWithElasticServer(serviceMaster1,"ServiceMaster",true,false);
      } catch (JsonProcessingException e) {
         unitServiceMapperLogger.error("Error in service master creation in elastic"+e.getMessage());
      }
      List<UnitServiceMapper> list1=null;
         List<UnitServiceMapper> unitMapper = unitServiceMapperRepository.findByServiceMaster(serviceMaster1);
      if(CollectionUtils.isNotEmpty(unitServiceMapperDTO.getUnitServiceMapper())){
         List<UnitServiceMapper> listUnitService=unitServiceMapperDTO.getUnitServiceMapper();
         for (UnitServiceMapper unitMapper1 : unitServiceMapperDTO.getUnitServiceMapper()) {
            //  UnitServiceMapper unitServiceMapper=null;
            if (unitMapper1.getActive() == null)
               unitMapper1.setActive(true);
            else
               unitMapper1.setActive(unitMapper1.getActive());
            unitMapper1.setIsAllowMultipleQuantity(unitMapper1.getIsAllowMultipleQuantity());
            unitMapper1.setIsDoctorRequired(unitMapper1.getIsDoctorRequired());
            unitMapper1.setServiceMaster(serviceMaster1);
            Long unitId = Long.parseLong(unitMapper1.getUnitMaster().getId());
            Optional<UnitMaster> unitMaster = unitMasterRepository.findById(unitId);
            unitMapper1.setUnitMaster(unitMaster.get());
            unitServiceMapper = unitServiceMapperRepository.save(unitMapper1);
            if (unitServiceMapper != null) {
               list.add(unitServiceMapper);
            }
            try {
               elasticSearchAssistant.syncWithElasticServer(unitServiceMapper,"UnitServiceMapper",true,false);
            } catch (JsonProcessingException e) {
               unitServiceMapperLogger.error("Error in unitServiceMapper creation in elastic"+e.getMessage());
            }
         }
      }
      return list;
   }


   public UnitServiceMapper getUnitServiceByUnitAndService(Long sMasterId, Long uMasterId) {
      UnitMaster unitMaster = unitMasterRepository.getOne(uMasterId);
      ServiceMaster serviceMaster = serviceMasterRepository.getOne(sMasterId);
      UnitServiceMapper uServiceMapper = unitServiceMapperRepository.findByServiceMasterAndUnitMaster(serviceMaster, unitMaster);
      return uServiceMapper;
   }

   /**
    * @param serviceMasterId
    *
    * @return
    */
   public List<UnitServiceMapper> getUnitServiceByService(Long serviceMasterId) {
      ServiceMaster serviceMaster = serviceMasterRepository.getOne(serviceMasterId);
      List<UnitServiceMapper> uServiceData = unitServiceMapperRepository.findByServiceMaster(serviceMaster);
      return uServiceData;

   }

   /**
    *  used for status change
    * @param id
    */
   public void updateStatusForUnit( Long id){
      unitServiceMapperRepository.updateStatusForUnit(id);
   }


}
