package com.sdgt.medcare.master.service.common;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.core.exceptions.DataException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.dto.common.ServiceDTO;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import com.sdgt.medcare.master.repository.common.GroupMasterRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.repository.common.SubGroupMasterRepository;
import com.sdgt.medcare.master.util.NumberParseAssistant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 * @author Pravat Kumar Pradhan
 */
@Service
public class ServicesService  {
    private Logger logger = LoggerFactory.getLogger(ServicesService.class);
    @Autowired
    private ServiceMasterRepository serviceMasterRepository;
    @Autowired
    private GroupMasterRepository groupMasterRepository;
    @Autowired
    private SubGroupMasterRepository subGroupMasterRepository;

    public ServicesService(ServiceMasterRepository serviceMasterRepository, GroupMasterRepository groupMasterRepository, SubGroupMasterRepository subGroupMasterRepository) {
        this.serviceMasterRepository = serviceMasterRepository;
        this.groupMasterRepository = groupMasterRepository;
        this.subGroupMasterRepository = subGroupMasterRepository;
    }

    /**
     * Find All {@link ServiceMaster}
     *
     * @return list of {@link ServiceDTO}
     */
    public Collection<ServiceDTO> findAll() {
        return translate(serviceMasterRepository.findAll());
    }

    /**
     * Find by Id {@link ServiceMaster}
     *
     * @param id given.
     * @return list of {@link ServiceDTO}
     */
    public Collection<ServiceDTO> findById(final String id) {
        final Optional<ServiceMaster> serviceMasterOptional = serviceMasterRepository.findById(NumberParseAssistant.parseLong(id));
        return serviceMasterOptional.map(serviceMaster -> translate(Collections.singleton(serviceMaster))).orElse(Collections.emptySet());
    }

    /**
     * Find by Ids {@link ServiceMaster}
     *
     * @param ids given.
     * @return list of {@link ServiceDTO}
     */
    public Collection<ServiceDTO> findByIds(final Collection<String> ids) {
        return translate(serviceMasterRepository.findAllById(ids.parallelStream()
                .map(NumberParseAssistant::parseLong)
                .collect(Collectors.toSet())));
    }

    /**
     * Find by codes {@link ServiceMaster}
     *
     * @param codes given.
     * @return list of {@link ServiceDTO}
     */
    public Collection<ServiceDTO> findByCodes(final Collection<String> codes) {
        return translate(serviceMasterRepository.findAllByCode(codes));
    }

    /**
     * Find by code {@link ServiceMaster}
     *
     * @param code given.
     * @return list of {@link ServiceDTO}
     */
    public Collection<ServiceDTO> findByCode(final String code) {
        final ServiceMaster serviceMaster = serviceMasterRepository.findByCodeIgnoreCase(code);

        if (serviceMaster == null) {
            logger.error("findByCode; code = {} not found", code);
            return Collections.emptySet();
        }

        return translate(Collections.singleton(serviceMaster));
    }

    /**
     * Find by description {@link ServiceMaster}
     *
     * @param desc given.
     * @return list of {@link ServiceDTO}
     */
    public Collection<ServiceDTO> findByDesc(final String desc) {
        final ServiceMaster serviceMaster = serviceMasterRepository.findByDescIgnoreCase(desc);

        if (serviceMaster == null) {
            logger.error("findByDesc; desc = {} not found", desc);
            return Collections.emptySet();
        }

        return translate(Collections.singleton(serviceMaster));
    }

    /**
     * Find by {@link GroupMaster#getCode()}
     * @param groupCode given.
     * @return list of {@link ServiceDTO}
     */
    public Collection<ServiceDTO> findByGroupCode(final String groupCode) {
        final GroupMaster groupMaster = groupMasterRepository.findByCodeIgnoreCase(groupCode);
        if (groupMaster == null) {
            logger.error("findByGroupCode; group code = {} not found", groupCode);
            return Collections.emptySet();
        }
        final Collection<ServiceMaster> serviceMasterCollection = serviceMasterRepository.findByGroupMaster(groupMaster);

        return translate(serviceMasterCollection);
    }

    /**
     * Find by {@link com.sdgt.medcare.master.entity.org.SubGroupMaster#getCode()}
     * @param subGroupCode given.
     * @return list of {@link ServiceDTO}
     */
    public Collection<ServiceDTO> findBySubGroupCode(final String subGroupCode) {
        final SubGroupMaster subGroupMaster = subGroupMasterRepository.findByCodeIgnoreCase(subGroupCode);
        if (subGroupMaster == null) {
            logger.error("findBySubGroupCode; sub group code = {} not found", subGroupCode);
            return Collections.emptySet();
        }
        final Collection<ServiceMaster> serviceMasterCollection = serviceMasterRepository.findByGroupMasterAndSubGroupMaster(subGroupMaster.getGroupMaster(), subGroupMaster);

        return translate(serviceMasterCollection);
    }

    /**
     * Translate Entity to DTO.
     *
     * @param serviceMasterCollection given.
     * @return list of {@link ServiceDTO}
     */
    private Collection<ServiceDTO> translate(final Collection<ServiceMaster> serviceMasterCollection) {
        if (CollectionUtils.isEmpty(serviceMasterCollection)) {
            return Collections.emptySet();
        }

        final Collection<ServiceDTO> dtoCollection = new HashSet<>(serviceMasterCollection.size());

        serviceMasterCollection.forEach(serviceMaster -> {
            final ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setId(serviceMaster.getId());
            serviceDTO.setCode(BaseMaster.getCode(serviceMaster));
            serviceDTO.setDesc(BaseMaster.getDesc(serviceMaster));
            serviceDTO.setGroupCode(BaseMaster.getCode(serviceMaster.getGroupMaster()));
            serviceDTO.setGroupDesc(BaseMaster.getDesc(serviceMaster.getGroupMaster()));
            serviceDTO.setSubGroupCode(BaseMaster.getCode(serviceMaster.getSubGroupMaster()));
            serviceDTO.setSubGroupDesc(BaseMaster.getDesc(serviceMaster.getSubGroupMaster()));
            dtoCollection.add(serviceDTO);
        });

        return dtoCollection;
    }


    /**
     * <p>Updated the status from true and false  with respect to the service Id</p>
     * @implNote
     * @param id   ServiceMaster id
     * @param serviceMasterObject    String value for to update
     * @return     ServiceMaster updated data
     */
    public ServiceMaster updateStatus(Long id,String serviceMasterObject) {
        Optional<ServiceMaster> serviceMaster=serviceMasterRepository.findById(id);
        ServiceMaster sMaster=new ServiceMaster();
        JsonObject obj = new JsonParser().parse(serviceMasterObject).getAsJsonObject();

        if(serviceMaster.isPresent()){
               sMaster=serviceMaster.get();
            String deletedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            sMaster.setUpdatedBy(deletedUserName);
            sMaster.setActive(obj.get("active").getAsBoolean());
        }
        return patch(sMaster,id.toString())  ;
    }

    /**
     *<p>Patch method which is need for the  update the ServiceMaster . </p>
     * @param objectToPatch    this is the Object of the ServiceMaster which need to of date
     * @param id   ServiceMaster id which data need to update
     * @return     updated the ServiceMaster Data
     */

    public ServiceMaster patch( ServiceMaster objectToPatch,String id){
        if (objectToPatch == null) {
            throw new DataException("There should exist an object already");
        }
        if (StringUtils.isNoneBlank(id)) {
            try {
                objectToPatch.setId(id);
                return serviceMasterRepository.save(objectToPatch);
            } catch (Exception e) {
                throw new DataException("persistence error from save", e);
            }
        }
        return null;
    }
}
