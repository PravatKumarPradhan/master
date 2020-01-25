package com.sdgt.medcare.master.service.lab;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.core.exceptions.DataException;
import com.core.service.BaseService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.entity.lab.ParameterHelpValuesMaster;
import com.sdgt.medcare.master.entity.lab.ParameterMaster;
import com.sdgt.medcare.master.entity.lab.ParameterReferenceRangeMaster;
import com.sdgt.medcare.master.entity.lab.ParameterTextualRangeMaster;
import com.sdgt.medcare.master.repository.lab.ParameterHelpValuesMasterRepository;
import com.sdgt.medcare.master.repository.lab.ParameterMasterRepository;
import com.sdgt.medcare.master.repository.lab.ParameterReferenceRangeMasterRepository;
import com.sdgt.medcare.master.repository.lab.ParameterTextualRangeMasterRepository;
import com.sdgt.medcare.master.util.JsonParserAssistant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

/**
 * @author Pravat Kumar Pradhan
 */
@Service
public class ParameterMasterService extends BaseService<ParameterMaster> {

   @Autowired
   ParameterMasterRepository parameterMasterRepository;

   ParameterHelpValuesMasterRepository parameterHelpValuesMasterRepository;
   ParameterTextualRangeMasterRepository parameterTextualRangeMasterRepository;
   ParameterReferenceRangeMasterRepository parameterReferenceRangeMasterRepository;

   @Autowired
   public ParameterMasterService(ParameterMasterRepository parameterMasterRepository,
                                 ParameterHelpValuesMasterRepository parameterHelpValuesMasterRepository,
                                 ParameterTextualRangeMasterRepository parameterTextualRangeMasterRepository, ParameterReferenceRangeMasterRepository parameterReferenceRangeMasterRepository) {
      super.setDao(parameterMasterRepository);

      this.parameterMasterRepository = parameterMasterRepository;
      this.parameterHelpValuesMasterRepository = parameterHelpValuesMasterRepository;
      this.parameterReferenceRangeMasterRepository = parameterReferenceRangeMasterRepository;
      this.parameterTextualRangeMasterRepository = parameterTextualRangeMasterRepository;
   }


   public Optional<ParameterMaster> getId(Long id) {
      Optional<ParameterMaster> parameterMasterDataById = parameterMasterRepository.findById(id);
      return parameterMasterDataById;
   }

   /**
    * @param parameterMaster
    *
    * @return
    */
   @Override
   @Transactional
   public ParameterMaster create(ParameterMaster parameterMaster) {
      ParameterMaster parameterObject = parameterMaster;
      parameterObject.setAliasName(parameterMaster.getAliasName());
      parameterObject.setDefaultComment(parameterMaster.getDefaultComment());
      parameterObject.setDeltaType(parameterMaster.getDeltaType());
      parameterObject.setFormulaDefinition(parameterMaster.getFormulaDefinition());
      if (parameterMaster.getCode() == null)
         parameterObject.setCode(parameterMaster.getDesc());

      parameterObject.setDesc(parameterMaster.getDesc());
      parameterObject.setMultiparameter(parameterMaster.getMultiparameter());
      parameterObject.setConstant(parameterMaster.getConstant());
      parameterObject.setFormula(parameterMaster.getFormula());
      parameterObject.setLabUnitMaster(parameterMaster.getLabUnitMaster());
      parameterObject.setDeltaNoOfDays(parameterMaster.getDeltaNoOfDays());
      parameterObject.setMethod(parameterMaster.getMethod());
      parameterObject.setDeltaPercentage(parameterMaster.getDeltaPercentage());
      parameterObject.setOptional(parameterMaster.getOptional());
      parameterObject.setFormulaParameters(parameterMaster.getFormulaParameters());
      parameterObject.setOrganizationMaster(parameterMaster.getOrganizationMaster());
      parameterObject.setPrintName(parameterMaster.getPrintName());

      String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
      parameterObject.setCreatedBy(createdBy);
      ParameterMaster savedParameter = parameterMasterRepository.save(parameterObject);
      Set<ParameterReferenceRangeMaster> parameterReferenceRangeMasters = null;
      Set<ParameterTextualRangeMaster> parameterTextualRangeMasters = null;
      Set<ParameterHelpValuesMaster> parameterHelpValue = null;
      if (CollectionUtils.isNotEmpty(savedParameter.getParameterReferenceRangeMasters())) {
         for (ParameterReferenceRangeMaster referenceRangeMaster : savedParameter.getParameterReferenceRangeMasters()) {
            referenceRangeMaster.setParameterMaster(savedParameter);
            /*if (referenceRangeMaster.getCode() == null) {
               referenceRangeMaster.setCode(referenceRangeMaster.getId());
            }*/
            if (referenceRangeMaster.getActive() == null) {
               referenceRangeMaster.setActive(true);
            } else
               referenceRangeMaster.setActive(referenceRangeMaster.getActive());
         }
      }
      if (CollectionUtils.isNotEmpty(savedParameter.getParameterTextualRangeMaster())) {
         Set<ParameterTextualRangeMaster> parameterTextualRangeMasterSet = savedParameter.getParameterTextualRangeMaster();
         for (ParameterTextualRangeMaster paramTextualRange : savedParameter.getParameterTextualRangeMaster()) {
            paramTextualRange.setParameterMaster(savedParameter);
            if (paramTextualRange.getActive() == null) {
               paramTextualRange.setActive(true);
            }
           /* if (paramTextualRange.getCode() == null)
               paramTextualRange.setCode(paramTextualRange.getId());*/
            paramTextualRange.setCreatedBy(createdBy);
         }
      }
      if (CollectionUtils.isNotEmpty(parameterMaster.getParameterHelpValuesMaster())) {
         Set<ParameterHelpValuesMaster> parameterHelpValuesMasterSet = savedParameter.getParameterHelpValuesMaster();
         for (ParameterHelpValuesMaster paramHelpValue : savedParameter.getParameterHelpValuesMaster()) {
            paramHelpValue.setParameterMaster(savedParameter);
            if (paramHelpValue.getActive() == null)
               paramHelpValue.setActive(true);
//            if (paramHelpValue.getCode() == null)
            paramHelpValue.setCreatedBy(createdBy);
         }
      }
      parameterMaster.setParameterReferenceRangeMasters(parameterReferenceRangeMasters);
      parameterMaster.setParameterTextualRangeMaster(parameterTextualRangeMasters);
      parameterMaster.setParameterHelpValuesMaster(parameterHelpValue);
      return savedParameter;
   }

   /**
    * @param pageable
    *
    * @return
    */
   public Page<ParameterMaster> getAllParameter(Pageable pageable) {
      return parameterMasterRepository.findAll(pageable);
   }

   public Optional<ParameterMaster> getById(Long parameterId) {
      return parameterMasterRepository.findById(parameterId);
   }

   public ParameterMaster deActiveParameter(String parameterValue, Long id) {

   //   Long parameterId = Long.parseLong(id);   commenting the code for the testing
      Optional<ParameterMaster> parameterMasterDataById = parameterMasterRepository.findById(id);
      JsonObject jsonObject = new JsonParser().parse(parameterValue).getAsJsonObject();
      ParameterMaster paramData = new ParameterMaster();
      if (parameterMasterDataById.isPresent()) {
         paramData = parameterMasterDataById.get();
         String deleteByUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
         paramData.setUpdatedBy(deleteByUserName);
         paramData.setActive(jsonObject.get("active").getAsBoolean());
      }
      return patch(paramData, id.toString());
   }


   @Override
   public ParameterMaster patch(ParameterMaster objectToPatch, String id) {
      if (objectToPatch == null) {
         throw new DataException("There should exist an object already");
      }
      if (StringUtils.isNoneBlank(id)) {
         try {
            objectToPatch.setId(id);
            return parameterMasterRepository.save(objectToPatch);
         } catch (Exception e) {
            throw new DataException("persistance error from save", e);
         }
      }
      return null;
   }

   @Override
   @Transactional
   public ParameterMaster update(ParameterMaster parameterMaster, String id) {
      Long parameterId = Long.parseLong(id);
      //  Optional<ParameterMaster> parameterMaster1 = parameterMasterRepository.findById(parameterId);
      String createdOrUpdate = HttpUtils.getHeader(HttpHeaders.USER_NAME);
      parameterMaster.setId(parameterId);
      if (CollectionUtils.isNotEmpty(parameterMaster.getParameterReferenceRangeMasters())) {
         Set<ParameterReferenceRangeMaster> referenceRangeMasterSet = parameterMaster.getParameterReferenceRangeMasters();
         {
            referenceRangeMasterSet.stream().forEach(rangeStream -> {
               if (rangeStream.getActive() == null)
                  rangeStream.setActive(true);
               rangeStream.setCreatedBy(createdOrUpdate);
               rangeStream.setParameterMaster(parameterMaster);
            });
         }
         parameterMaster.setParameterReferenceRangeMasters(referenceRangeMasterSet);
      }
      if (CollectionUtils.isNotEmpty(parameterMaster.getParameterTextualRangeMaster())) {
         Set<ParameterTextualRangeMaster> textualRangeMasterSet = parameterMaster.getParameterTextualRangeMaster();
         {
            textualRangeMasterSet.stream().forEach(streamTextualRange -> {
               streamTextualRange.setCreatedBy(createdOrUpdate);
               if (streamTextualRange.getActive() == null)
                  streamTextualRange.setActive(true);
               streamTextualRange.setParameterMaster(parameterMaster);
            });

         }
      }
      if (CollectionUtils.isNotEmpty(parameterMaster.getParameterHelpValuesMaster())) {
         Set<ParameterHelpValuesMaster> parameterHelpValuesMasterSet = parameterMaster.getParameterHelpValuesMaster();
         parameterHelpValuesMasterSet.stream().forEach(helpValueStreamObj -> {
            helpValueStreamObj.setCreatedBy(createdOrUpdate);
            if (helpValueStreamObj.getActive() == null)
               helpValueStreamObj.setActive(true);
            helpValueStreamObj.setParameterMaster(parameterMaster);

         });
      }
      return parameterMasterRepository.save(parameterMaster);
   }

   public Optional<ParameterMaster> findById(Long id) {
      return parameterMasterRepository.findById(id);
   }

   @Transactional
   public void upDateHealpStatus(Long id) {
      parameterHelpValuesMasterRepository.updateStatus(id);
   }


   @Transactional
   public void updateRefRangeStatus(Long id) {
      parameterReferenceRangeMasterRepository.updateStatus(id);
   }

   @Transactional
   public void updateTextRangeStatus(Long id) {
      parameterTextualRangeMasterRepository.updateStatus(id);

   }

   /**
    * @param id   id of the entityu
    * @param type the type of the entity
    */
   @Transactional
   public void updateStatusByType(Long id, String type) {
      JsonObject jsonObject = JsonParserAssistant.getJsonObject(type);
      String s = jsonObject.get("type").getAsString();
      if (s.equalsIgnoreCase("RV")) {
         parameterReferenceRangeMasterRepository.updateStatus(id);
      } else if (s.equalsIgnoreCase("TR")) {
         parameterTextualRangeMasterRepository.updateStatus(id);
      } else if (s.equalsIgnoreCase("HV")) {
         parameterHelpValuesMasterRepository.updateStatus(id);
      }
   }

}
