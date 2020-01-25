package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "m_frequency_medication_time_mapper",schema = "clinical")
public class FrequencyMedicationTimeMapper extends BaseMaster {

    public FrequencyMaster getFrequencyMaster() {
        return frequencyMaster;
    }

    public void setFrequencyMaster(FrequencyMaster frequencyMaster) {
        this.frequencyMaster = frequencyMaster;
    }

    public MedicationTimeMaster getMedicationTimeMaster() {
        return medicationTimeMaster;
    }

    public void setMedicationTimeMaster(MedicationTimeMaster medicationTimeMaster) {
        this.medicationTimeMaster = medicationTimeMaster;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frequencyId")
    private FrequencyMaster frequencyMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicationTimeId")
    private MedicationTimeMaster medicationTimeMaster;

}
