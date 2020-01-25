package com.sdgt.medcare.master.service.clinical;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.clinical.VitalReferanceRangeMaster;
import com.sdgt.medcare.master.repository.clinical.VitalReferanceRangeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VitalReferanceRangeMasterService extends BaseService<VitalReferanceRangeMaster> {

    @Autowired
    VitalReferanceRangeMasterRepository vitalReferanceRangeMasterRepository;

    public List<VitalReferanceRangeMaster> getAllVitals(String speciality, Long genderId, Integer ageInDays){
        System.out.println("Vital Service called");
        List<VitalReferanceRangeMaster> allVitals=
                vitalReferanceRangeMasterRepository.findAllVitals(speciality,genderId,ageInDays);
        return allVitals;
    }
}
