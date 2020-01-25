package com.sdgt.medcare.master.service.common;

import com.sdgt.medcare.master.dto.otims.CheckListDTO;
import com.sdgt.medcare.master.entity.global.CheckListMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.repository.common.CheckListRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CheckListService {
    private Logger logger = LoggerFactory.getLogger(CheckListService.class);

    @Autowired
    CheckListRepository checkListRepository;
    @Autowired private OrganizationMasterRepository organizationMasterRepository;
    @Autowired private UnitMasterRepository unitMasterRepository;


    @Transactional
    public CheckListMaster create(CheckListDTO checkListDTO){
        verifyNotNull(checkListDTO);
        logger.info("CheckList Master create");


        final OrganizationMaster organizationMaster = findAndValidateOrgCode(checkListDTO.getOrgCode());
       final UnitMaster unitMaster = findAndValidateUnitCode(checkListDTO.getUnitCode());

        final CheckListMaster checkListObj=new CheckListMaster();
        checkListObj.setActive(true);
        checkListObj.setCode(checkListDTO.getCode());
        checkListObj.setCheckListStatus(checkListDTO.getCheckListStatus());
        checkListObj.setCheckListHeader(checkListDTO.getCheckListHeader());
        checkListObj.setCheckListType(checkListDTO.getCheckListType());
        checkListObj.setContentDetails(checkListDTO.getContentDetails());
        checkListObj.setManditory(checkListDTO.isManditory());
        checkListObj.setUnitMaster(unitMaster);
        checkListObj.setModule(checkListDTO.getModule());

        checkListRepository.saveAndFlush(checkListObj);
        return checkListObj;
    }

    @Transactional
    public CheckListMaster updateCheckList(long id,CheckListDTO checkListDTO){
        CheckListMaster patchObject=checkListRepository.findById(id).orElse(null);
        if(checkListDTO.getCheckListType()!=null && !checkListDTO.getCheckListType().isEmpty()){
            patchObject.setCheckListType(checkListDTO.getCheckListType());
        }
        if(checkListDTO.getCheckListStatus()!=null && !checkListDTO.getCheckListStatus().isEmpty()){
            patchObject.setCheckListStatus(checkListDTO.getCheckListStatus());
        }
        if(checkListDTO.getContentDetails()!=null && !checkListDTO.getContentDetails().isEmpty()){
            patchObject.setContentDetails(checkListDTO.getContentDetails());
        }
        if(checkListDTO.getCheckListHeader()!=null && !checkListDTO.getCheckListHeader().isEmpty()){
            patchObject.setCheckListHeader(checkListDTO.getCheckListHeader());
        }
        if(checkListDTO.getModule()!=null && !checkListDTO.getModule().isEmpty()){
            patchObject.setModule(checkListDTO.getModule());
        }

        return checkListRepository.save(patchObject);
    }

    /**
     * Validates and finds the {@link UnitMaster} for the given unit code,
     *
     * @param unitCode a valid unit code.
     * @return {@link UnitMaster} if valid. Else null.
     * @throws IllegalArgumentException if unit header missing.
     */
    private UnitMaster findAndValidateUnitCode(final String unitCode) {
        if (StringUtils.isBlank(unitCode)) {
            logger.error("Unit header missing. {}", unitCode);
            throw new IllegalArgumentException("Unit header missing");
        }

        return unitMasterRepository.findByCode(unitCode);
    }

    /**
     * Validates and finds the {@link OrganizationMaster} for the given org code,
     *
     * @param orgCode a valid org code.
     * @return {@link OrganizationMaster} if valid. Else null.
     * @throws IllegalArgumentException if org code missing.
     */
    private OrganizationMaster findAndValidateOrgCode(final String orgCode) {
        if (StringUtils.isBlank(orgCode)) {
            logger.error("Unit header missing. {}", orgCode);
            throw new IllegalArgumentException("Org code missing");
        }

        return organizationMasterRepository.findByCode(orgCode);
    }

    /**
     * Validates the given DTO.
     * @param object a dto.
     */
    private void verifyNotNull(final Object object) {
        if(object == null) {
            logger.error("invalid request.");
            throw new IllegalArgumentException("invalid request.");
        }
    }



}

