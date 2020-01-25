package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_goal_intervention_mapper",schema = "clinical")
public class GoalInterventionMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_code",referencedColumnName = "code")
    private GoalMaster goalMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervention_code",referencedColumnName = "code")
    private InterventionMaster interventionMaster;

    public GoalMaster getGoalMaster() {
        return goalMaster;
    }

    public void setGoalMaster(GoalMaster goalMaster) {
        this.goalMaster = goalMaster;
    }

    public InterventionMaster getInterventionMaster() {
        return interventionMaster;
    }

    public void setInterventionMaster(InterventionMaster interventionMaster) {
        this.interventionMaster = interventionMaster;
    }
}
