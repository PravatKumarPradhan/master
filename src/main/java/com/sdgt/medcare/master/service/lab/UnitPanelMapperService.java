package com.sdgt.medcare.master.service.lab;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.lab.UnitPanelMapper;
import com.sdgt.medcare.master.repository.lab.UnitPanelMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitPanelMapperService extends BaseService<UnitPanelMapper> {

    UnitPanelMapperRepository unitPanelMapperRepository;

    @Autowired
    public void setDao(UnitPanelMapperRepository unitPanelMapperRepository) {
        super.setDao(unitPanelMapperRepository);
        this.unitPanelMapperRepository = unitPanelMapperRepository;
    }

    public UnitPanelMapper getPanelByUnit(String unitCode, Long panelId, Long genderId, Integer ageInDays) {
        List<UnitPanelMapper> unitPanelMappers = unitPanelMapperRepository.findForNumericParams(unitCode, panelId, genderId, ageInDays);
        if (unitPanelMappers == null || unitPanelMappers.isEmpty()) {
            unitPanelMappers = unitPanelMapperRepository.findTestForNoParameters(unitCode, panelId);
        }
        if (unitPanelMappers != null && !unitPanelMappers.isEmpty())
        {
            return unitPanelMappers.get(0);
        }
        return null;
    }

    public UnitPanelMapper getPanelByUnitAndService(String unitCode, Long serviceId, Long genderId, Integer ageInDays) {
        List<UnitPanelMapper> unitPanelMappers = unitPanelMapperRepository.findForNumericParamsByServiceId(unitCode, serviceId, genderId, ageInDays);
        if (unitPanelMappers == null || unitPanelMappers.isEmpty()) {
            unitPanelMappers = unitPanelMapperRepository.findTestForNoParametersByService(unitCode, serviceId);
        }
        if (unitPanelMappers != null && !unitPanelMappers.isEmpty())
        {
            return unitPanelMappers.get(0);
        }
        return null;
    }
}
