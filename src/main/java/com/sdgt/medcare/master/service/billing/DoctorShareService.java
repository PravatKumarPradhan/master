package com.sdgt.medcare.master.service.billing;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.core.exceptions.DataException;
import com.core.service.BaseService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.entity.unit.DoctorShareMaster;
import com.sdgt.medcare.master.repository.billing.DoctorShareRepository;
import com.sdgt.medcare.master.repository.common.EmployeeRepository;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.util.BaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorShareService extends BaseService<DoctorShareMaster> {

    private DoctorShareRepository dao;

    private DoctorShareRepository doctorShareRepository;

    @Autowired
    private ServiceMasterRepository serviceMasterRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public void setDao(DoctorShareRepository dao) {
        super.setDao(dao);
        this.dao = dao;
    }
    public DoctorShareService(DoctorShareRepository dao) {
        super.setDao(dao);
        this.doctorShareRepository = dao;
    }


    @Transactional
    public List<DoctorShareMaster> create(List<DoctorShareMaster> doctorShareMasterlist){

        for (DoctorShareMaster doctorShareMaster : doctorShareMasterlist)
        {
            if(doctorShareMaster != null) {
            doctorShareMaster.setCreatedBy(HttpUtils.getHeader(HttpHeaders.USER_NAME));

                if (BaseUtil.isNullOrEmptyObject(doctorShareMaster.getActive())) {
                    doctorShareMaster.setActive(true);
                } else {
                    doctorShareMaster.setActive(doctorShareMaster.getActive());
                }
            }
        }

        return dao.saveAll(doctorShareMasterlist);
    }

    /**
     * @param pageable
     * @return
     */
    public Page<DoctorShareMaster> getAllDoctorShare(Pageable pageable) {
        return dao.findAll(pageable);
    }

    /**
     * @param id
     * @return
     */

    public Optional<DoctorShareMaster> findById(Long id) {
        return doctorShareRepository.findById(id);
    }

    /**
     * @param id
     * @param DoctorShareJsonObject
     * @return
     */
    public DoctorShareMaster deActiveDoctorShare(Long id, String DoctorShareJsonObject) {

        Optional<DoctorShareMaster> findDoctorShareObject = dao.findById(id);
        DoctorShareMaster doctorShareMaster = new DoctorShareMaster();
        JsonObject obj = new JsonParser().parse(DoctorShareJsonObject).getAsJsonObject();
        if (findDoctorShareObject.isPresent()) {
            doctorShareMaster = findDoctorShareObject.get();
            String deletedUserName = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            doctorShareMaster.setUpdatedBy(deletedUserName);
            doctorShareMaster.setActive(obj.get("active").getAsBoolean());
        }
        return patch(doctorShareMaster, id.toString());
    }

    /**
     * @param objectToPatch
     * @param id
     * @return
     */
    @Override
    public DoctorShareMaster patch(DoctorShareMaster objectToPatch, String id) {
        if (objectToPatch == null) {
            throw new DataException("There should exist an object already");
        }
        if (StringUtils.isNoneBlank(id)) {
            try {
                objectToPatch.setId(id);
                return doctorShareRepository.save(objectToPatch);
            } catch (Exception e) {
                throw new DataException("persistance error from save", e);
            }
        }
        return null;
    }

    /**
     * @implNote
     * @param doctorShareMaster
     * @param Id
     * @return
     */
    @Transactional
    @Override
    public DoctorShareMaster update(DoctorShareMaster doctorShareMaster, String Id) {
        String createdOrUpdatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        doctorShareMaster.setId(Long.parseLong(Id));
       return doctorShareRepository.save(doctorShareMaster);
    }


    /**
     * @param servicecode
     * @return
     */
    public DoctorShareMaster getDoctorShareRule(String servicecode,long doctorId,Long docDeptId) {

        DoctorShareMaster doctorShareMaster = new DoctorShareMaster();
        List<DoctorShareMaster> doctorShareMasterList = new ArrayList<>();

        Long ServiceGroupId = serviceMasterRepository.findGroupByServiceCode(servicecode);
        Long ServiceSubGroupId = serviceMasterRepository.findSubGroupByServiceCode(servicecode);
        boolean IsDoctorShareServcie = getIsDoctorShareService(servicecode);
        boolean IsDoctorShareDoctor =getIsDoctorShareDoctor(doctorId);

        if(IsDoctorShareServcie == true && IsDoctorShareDoctor==true ){

            doctorShareMasterList = dao.findDoctorShareByServiceCode(
                    servicecode,doctorId,null,null,null);

            if(doctorShareMasterList == null && doctorShareMasterList.isEmpty()){
                doctorShareMasterList = dao.findDoctorShareByServiceCode(servicecode,null,docDeptId,null,null);
                if(doctorShareMasterList == null && doctorShareMasterList.isEmpty()){
                    doctorShareMasterList = dao.findDoctorShareByServiceCode(null,doctorId,null,null,ServiceSubGroupId);
                    if(doctorShareMasterList == null && doctorShareMasterList.isEmpty()){
                        doctorShareMasterList = dao.findDoctorShareByServiceCode(null,doctorId,null,ServiceGroupId,null);
                        if(doctorShareMasterList == null && doctorShareMasterList.isEmpty()){
                            doctorShareMasterList = dao.findDoctorShareByServiceCode(null,null,docDeptId,ServiceGroupId,null);
                            if(doctorShareMasterList == null && doctorShareMasterList.isEmpty()){
                                doctorShareMasterList = dao.findDoctorShareByServiceCode(null,null,docDeptId,null,ServiceSubGroupId);
                            }
                        }
                    }
                }
            }
            if(doctorShareMasterList != null && !doctorShareMasterList.isEmpty())
            {
                doctorShareMaster = doctorShareMasterList.get(0);
            }
        }
        else {
            throw new IllegalArgumentException("Either Service or Doctor does not have set is_doctorshare flag to true");
        }

        return doctorShareMaster;
    }

    private  boolean getIsDoctorShareService(String servicecode){
        Boolean result=false;
        if (StringUtils.isNotBlank(servicecode)) {
            result = serviceMasterRepository.findDoctorShareServiceByServiceCode(servicecode);
            if(result == null)
                result=false;
        }
           return result;
    }

    private  boolean getIsDoctorShareDoctor(Long doctorId){
        Boolean result=false;
        if (doctorId != null) {
            result = employeeRepository.findDoctorShareDoctorById(doctorId);
            if(result == null)
                result=false;
        }
        return result;
    }
}
