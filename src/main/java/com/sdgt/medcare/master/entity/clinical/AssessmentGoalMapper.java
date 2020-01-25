package com.sdgt.medcare.master.entity.clinical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_assessment_goal_mapper",schema = "clinical")
public class AssessmentGoalMapper extends BaseMaster {


    public GoalMaster getGoalMaster() {
        return goalMaster;
    }

    public void setGoalMaster(GoalMaster goalMaster) {
        this.goalMaster = goalMaster;
    }

    public AssessmentMaster getAssessmentMaster() {
        return assessmentMaster;
    }

    public void setAssessmentMaster(AssessmentMaster assessmentMaster) {
        this.assessmentMaster = assessmentMaster;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_code",referencedColumnName = "code")
    private GoalMaster goalMaster;

    @JsonIgnoreProperties({"goalInterventionMappers","assessmentMaster"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_code",referencedColumnName = "code")
    private AssessmentMaster assessmentMaster;
}
