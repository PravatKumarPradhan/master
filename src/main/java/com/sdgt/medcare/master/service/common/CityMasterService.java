package com.sdgt.medcare.master.service.common;


import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.global.CityMaster;
import com.sdgt.medcare.master.repository.common.CityMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityMasterService  extends BaseService<CityMaster> {
    @Autowired
    CityMasterRepository cityMasterRepository;

    public   CityMasterService(CityMasterRepository cityMasterRepository){
        this.cityMasterRepository=cityMasterRepository;
    }

}
