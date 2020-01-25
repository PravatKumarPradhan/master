package com.sdgt.medcare.master.service.clinical;


import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.clinical.GoalInterventionMapper;
import com.sdgt.medcare.master.repository.clinical.GoalInterventionMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalInterventionMapperService extends BaseService<GoalInterventionMapper> {

    @Autowired
    GoalInterventionMapperRepository goalInterventionMapperRepository;

    public List<GoalInterventionMapper> getInterventions (List<String> goals){
        return goalInterventionMapperRepository.findAllByGoalMasterCodeIn(goals);
    }
}
