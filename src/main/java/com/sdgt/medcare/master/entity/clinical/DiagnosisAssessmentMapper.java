package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_diagnosis_assessment_mapper",schema = "clinical")
public class DiagnosisAssessmentMapper extends BaseMaster {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_code",referencedColumnName = "code")
    private AssessmentMaster assessmentMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_code",referencedColumnName = "code")
    private DiagnosisMaster diagnosisMaster;

    public AssessmentMaster getAssessmentMaster() {
        return assessmentMaster;
    }

    public void setAssessmentMaster(AssessmentMaster assessmentMaster) {
        this.assessmentMaster = assessmentMaster;
    }

    public DiagnosisMaster getDiagnosisMaster() {
        return diagnosisMaster;
    }

    public void setDiagnosisMaster(DiagnosisMaster diagnosisMaster) {
        this.diagnosisMaster = diagnosisMaster;
    }
}
