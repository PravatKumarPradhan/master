package com.sdgt.medcare.master.entity.clinical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity()
@Table(name = "m_frequency",schema = "clinical")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class FrequencyMaster extends BaseMaster {


    private String abbreviation;
    private Long nextDoseIntervalHrs;
    private Long numberOfDays;
    private Long numberOfFrequency;

    public Long getNumberOfFrequency() {
        return numberOfFrequency;
    }

    public void setNumberOfFrequency(Long numberOfFrequency) {
        this.numberOfFrequency = numberOfFrequency;
    }

    public Set<FrequencyMedicationTimeMapper> getFrequencyMedicationTimeMappers() {
        return frequencyMedicationTimeMappers;
    }

    public void setFrequencyMedicationTimeMappers(Set<FrequencyMedicationTimeMapper> frequencyMedicationTimeMappers) {
        this.frequencyMedicationTimeMappers = frequencyMedicationTimeMappers;
    }

    @JsonIgnoreProperties("frequencyMaster")
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "frequencyMaster")
    Set<FrequencyMedicationTimeMapper> frequencyMedicationTimeMappers;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Long getNextDoseIntervalHrs() {
        return nextDoseIntervalHrs;
    }

    public void setNextDoseIntervalHrs(Long nextDoseIntervalHrs) {
        this.nextDoseIntervalHrs = nextDoseIntervalHrs;
    }

    public Long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }


}
