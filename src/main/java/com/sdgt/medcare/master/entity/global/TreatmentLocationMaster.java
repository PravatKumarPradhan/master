package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_treatment_location",schema = "adt")
public class TreatmentLocationMaster extends BaseMaster {

    public TreatmentLocationMaster() {
    }
}
