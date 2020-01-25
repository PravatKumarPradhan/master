package com.sdgt.medcare.master.controller.clinicare;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.entity.clinical.GoalInterventionMapper;
import com.sdgt.medcare.master.service.clinical.GoalInterventionMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/GoalIntervention")
public class GoalInterventionMapperController extends RestWSController<GoalInterventionMapper> {

    @Autowired
    GoalInterventionMapperService goalInterventionMapperService;

    @GetMapping("/listByGoalCode")
    public List<GoalInterventionMapper> getInterventionList(
            @RequestParam(name = "goal", required = false) List<String> goal
    ){
        System.out.println("GetIntervention");
        return goalInterventionMapperService.getInterventions(goal);
    }
}
