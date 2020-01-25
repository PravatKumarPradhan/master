package com.sdgt.medcare.master.entity.clinical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name="m_condition_diagnosis_mapper",schema = "clinical")
public class ConditionDiagnosisMapper  extends BaseMaster {


    public ClinicalConditionMaster getClinicalConditionMaster() {
        return clinicalConditionMaster;
    }

    public void setClinicalConditionMaster(ClinicalConditionMaster clinicalConditionMaster) {
        this.clinicalConditionMaster = clinicalConditionMaster;
    }

    public DiagnosisMaster getDiagnosisMaster() {
        return diagnosisMaster;
    }

    public void setDiagnosisMaster(DiagnosisMaster diagnosisMaster) {
        this.diagnosisMaster = diagnosisMaster;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_code",referencedColumnName = "code")
    private ClinicalConditionMaster clinicalConditionMaster;

    @JsonIgnoreProperties({"diagnosisAssessmentMappers","diagnosisGoalMappers"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_code",referencedColumnName = "code")
    private DiagnosisMaster diagnosisMaster;
}
