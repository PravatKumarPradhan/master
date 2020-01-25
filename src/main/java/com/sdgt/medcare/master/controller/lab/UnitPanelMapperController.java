package com.sdgt.medcare.master.controller.lab;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.lab.UnitPanelMapper;
import com.sdgt.medcare.master.service.lab.UnitPanelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
@CrossOrigin
@RequestMapping({"/UnitWisePanels","/unitWisePanels"})
public class UnitPanelMapperController extends RestWSController<UnitPanelMapper> {

    @Autowired
    UnitPanelMapperService unitPanelMapperService;

    @GetMapping("/panel/{id}")
    public UnitPanelMapper getPanelByPanelId(@QueryParam("genderId") Long genderId, @QueryParam("unitCode") String unitCode
            , @QueryParam("ageInDays") Integer ageInDays, @PathVariable("id") Long id){
        return unitPanelMapperService.getPanelByUnit(unitCode,id,genderId,ageInDays);
    }

    @GetMapping("/panel")
    public UnitPanelMapper getPanelByServiceId(@QueryParam("genderId") Long genderId, @QueryParam("unitCode") String unitCode
            , @QueryParam("ageInDays") Integer ageInDays, @QueryParam("serviceId") Long serviceId){
        return unitPanelMapperService.getPanelByUnitAndService(unitCode,serviceId,genderId,ageInDays);
    }
}
