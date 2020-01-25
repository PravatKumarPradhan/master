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

@Table(name="m_clinical_condition",schema = "clinical")
@Entity
public class ClinicalConditionMaster extends BaseMaster implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ClinicalConditionMaster() {
    }

    @Column(name ="is_checked")
    private Boolean isChecked=false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name ="type")
    private String type;


    public Set<ConditionDiagnosisMapper> getConditionDiagnosisMappers() {
        return conditionDiagnosisMappers;
    }

    public void setConditionDiagnosisMappers(Set<ConditionDiagnosisMapper> conditionDiagnosisMappers) {
        this.conditionDiagnosisMappers = conditionDiagnosisMappers;
    }

    @JsonIgnoreProperties("clinicalConditionMaster")
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy="clinicalConditionMaster")
    Set<ConditionDiagnosisMapper> conditionDiagnosisMappers;

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        isChecked = isChecked;
    }


}
