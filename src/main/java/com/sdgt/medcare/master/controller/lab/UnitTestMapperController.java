package com.sdgt.medcare.master.controller.lab;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.lab.UnitTestMapper;
import com.sdgt.medcare.master.service.lab.UnitTestMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
@CrossOrigin
@RequestMapping({"/UnitWiseTests","/unitWiseTests"})
public class UnitTestMapperController extends RestWSController<UnitTestMapper> {

    @Autowired
    UnitTestMapperService unitTestMapperService;

    @GetMapping("test/{id}")
    public UnitTestMapper getBedByEr(@QueryParam("genderId") Long genderId,@QueryParam("unitCode") String unitCode
                ,@QueryParam("ageInDays") Integer ageInDays,@PathVariable("id") Long id){
        return unitTestMapperService.getTestMastersByUnit(unitCode,id,genderId,ageInDays);
    }
}
