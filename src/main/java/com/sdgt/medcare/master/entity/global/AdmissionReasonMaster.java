package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_admission_reason",schema = "adt")
public class AdmissionReasonMaster extends BaseMaster {

    public AdmissionReasonMaster() {
    }
}
