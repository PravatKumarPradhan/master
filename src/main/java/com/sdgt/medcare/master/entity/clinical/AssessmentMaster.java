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
@Table(name= "m_assessment",schema = "clinical")
public class AssessmentMaster  extends BaseMaster implements Serializable {


    public AssessmentMaster() {
    }

    @Column(name ="is_checked")
    private Boolean isChecked=false;

    public Set<AssessmentGoalMapper> getAssessmentGoalMappers() {
        return assessmentGoalMappers;
    }

    public void setAssessmentGoalMappers(Set<AssessmentGoalMapper> assessmentGoalMappers) {
        this.assessmentGoalMappers = assessmentGoalMappers;
    }

    @JsonIgnoreProperties("assessmentMaster")
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy="assessmentMaster")
    Set<AssessmentGoalMapper> assessmentGoalMappers;


    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        isChecked = isChecked;
    }
}


