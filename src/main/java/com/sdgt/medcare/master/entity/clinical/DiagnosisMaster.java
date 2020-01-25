package com.sdgt.medcare.master.entity.clinical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="m_diagnosis",schema = "clinical")
public class DiagnosisMaster extends BaseMaster implements Serializable {

    public DiagnosisMaster() {
    }

    @Column(name ="is_checked")
    private Boolean isChecked=false;

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        isChecked = isChecked;
    }

    public Set<DiagnosisAssessmentMapper> getDiagnosisAssessmentMappers() {
        return diagnosisAssessmentMappers;
    }

    public void setDiagnosisAssessmentMappers(Set<DiagnosisAssessmentMapper> diagnosisAssessmentMappers) {
        this.diagnosisAssessmentMappers = diagnosisAssessmentMappers;
    }

//    public Set<DiagnosisGoalAssessmentMapper> getDiagnosisGoalMappers() {
//        return diagnosisGoalMappers;
//    }
//
//    public void setDiagnosisGoalMappers(Set<DiagnosisGoalAssessmentMapper> diagnosisGoalMappers) {
//        this.diagnosisGoalMappers = diagnosisGoalMappers;
//    }

    @JsonIgnoreProperties("diagnosisMaster")
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy="diagnosisMaster")
    Set<DiagnosisAssessmentMapper> diagnosisAssessmentMappers;

    @JsonIgnoreProperties("diagnosisMaster")
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy="diagnosisMaster")
    Set<DiagnosisGoalMapper> diagnosisGoalMappers;

//    @JsonIgnoreProperties("diagnosisMaster")
//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.LAZY,mappedBy="diagnosisMaster")
//    Set<DiagnosisGoalAssessmentMapper> diagnosisGoalMappers;

}
