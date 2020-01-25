package com.sdgt.medcare.master.controller.clinicare;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.clinical.VitalReferanceRangeMaster;
import com.sdgt.medcare.master.service.clinical.VitalReferanceRangeMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping({"/VitalMaster","/vitalMaster"})
public class VitalReferanceRangeMasterController extends RestWSController<VitalReferanceRangeMaster> {

    @Autowired
    VitalReferanceRangeMasterService vitalReferanceRangeMasterService;

    @GetMapping("all/{speciality}")
    public List<VitalReferanceRangeMaster> getAllVitals(
            @QueryParam("genderId") Long genderId,
            @QueryParam("ageInDays") Integer ageInDays,
            @PathVariable("speciality") String speciality){

        return vitalReferanceRangeMasterService.getAllVitals(speciality,genderId,ageInDays);
    }
}
