package com.sdgt.medcare.master.controller.clinicare;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.clinical.AssessmentMaster;
import com.sdgt.medcare.master.entity.clinical.DiagnosisGoalAssessmentMapper;
import com.sdgt.medcare.master.entity.clinical.DiagnosisMaster;
import com.sdgt.medcare.master.service.clinical.DiagnosisGoalAssessmentMapperService;
import com.sdgt.medcare.master.util.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/diagnosisGoalAssessment")
public class DiagnosisGoalAssessmentMapperController extends RestWSController<DiagnosisGoalAssessmentMapper> {

    @Autowired
    DiagnosisGoalAssessmentMapperService diagnosisGoalAssessmentMapperService;

    @Autowired
    private AbstractJpaDao dao;

    @GetMapping("/list")
    public ResponseEntity getConditionList(
            @RequestParam(name = "diagnosis", required = false) List<String> diagnosis,
            @RequestParam(name = "assessment", required = false) List<String> assessment
    ){
        System.out.println("Function Called");
        List<Object> goalData= new ArrayList<>();
        goalData.addAll(dao.findByMultiQueryByCode(DiagnosisMaster.class, "code", diagnosis));
        //goalData.addAll(dao.findByMultiQueryByCode(AssessmentMaster.class, "code", assessment));
        return ResponseEntity.ok(goalData);
    }

}
