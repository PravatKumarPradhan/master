package com.sdgt.medcare.master.service.lab;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.lab.UnitTestMapper;
import com.sdgt.medcare.master.repository.lab.UnitTestMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitTestMapperService extends BaseService<UnitTestMapper> {

    UnitTestMapperRepository unitTestMapperRepository;

    @Autowired
    public void setDao(UnitTestMapperRepository unitTestMapperRepository) {
        super.setDao(unitTestMapperRepository);
        this.unitTestMapperRepository = unitTestMapperRepository;
    }

    public UnitTestMapper getTestMastersByUnit(String unitCode, Long testId, Long genderId, Integer ageInDays)
    {
        List<UnitTestMapper> unitTestMappers=
                unitTestMapperRepository.findTestForParams(unitCode,testId,genderId,ageInDays);
        if(unitTestMappers==null || unitTestMappers.isEmpty())
        {
            unitTestMappers= unitTestMapperRepository.findTestForTextualParameters(unitCode,testId);

            if(unitTestMappers==null || unitTestMappers.isEmpty())
            {
                unitTestMappers= unitTestMapperRepository.findTestForNoParameters(unitCode,testId);

            }
        }
        if(unitTestMappers!=null && !unitTestMappers.isEmpty()) {
            return unitTestMappers.get(0);
        }
        return null;
    }


}
