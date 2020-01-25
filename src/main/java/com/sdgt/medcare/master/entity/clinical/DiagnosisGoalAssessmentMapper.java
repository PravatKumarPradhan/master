package com.sdgt.medcare.master.entity.clinical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_diagnosis_assessment_goal_mapper",schema = "clinical")
public class DiagnosisGoalAssessmentMapper extends BaseMaster {

    @JsonIgnoreProperties("diagnosisGoalAssessmentMappers")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_code",referencedColumnName = "code")
    private GoalMaster goalMaster;

    @JsonIgnoreProperties("diagnosisAssessmentMappers")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_code",referencedColumnName = "code")
    private DiagnosisMaster diagnosisMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_code",referencedColumnName = "code")
    private AssessmentMaster assessmentMaster;

    public AssessmentMaster getAssessmentMaster() {
        return assessmentMaster;
    }

    public void setAssessmentMaster(AssessmentMaster assessmentMaster) {
        this.assessmentMaster = assessmentMaster;
    }

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

    public DiagnosisGoalAssessmentMapper() {
    }

	public DiagnosisGoalAssessmentMapper(GoalMaster goalMaster, DiagnosisMaster diagnosisMaster,
			AssessmentMaster assessmentMaster) {
		super();
		this.goalMaster = goalMaster;
		this.diagnosisMaster = diagnosisMaster;
		this.assessmentMaster = assessmentMaster;
	}


}
