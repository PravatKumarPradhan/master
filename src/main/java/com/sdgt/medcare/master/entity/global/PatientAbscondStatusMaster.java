package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_patient_abscond_status",schema = "adt")
public class PatientAbscondStatusMaster extends BaseMaster {

    public PatientAbscondStatusMaster() {
    }
}
