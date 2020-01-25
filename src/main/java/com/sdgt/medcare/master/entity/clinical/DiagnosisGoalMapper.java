package com.sdgt.medcare.master.entity.clinical;


import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_diagnosis_goal_mapper",schema = "clinical")
public class DiagnosisGoalMapper extends BaseMaster {

    public GoalMaster getGoalMaster() {
        return goalMaster;
    }

    public void setGoalMaster(GoalMaster goalMaster) {
        this.goalMaster = goalMaster;
    }

    public DiagnosisMaster getDiagnosisMaster() {
        return diagnosisMaster;
    }

    public void setDiagnosisMaster(DiagnosisMaster diagnosisMaster) {
        this.diagnosisMaster = diagnosisMaster;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_code",referencedColumnName = "code")
    private DiagnosisMaster diagnosisMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_code",referencedColumnName = "code")
    private GoalMaster goalMaster;
}
