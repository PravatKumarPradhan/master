package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_occupation")
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
public class OccupationMaster extends BaseMaster {


    public OccupationMaster() {
    }
}
