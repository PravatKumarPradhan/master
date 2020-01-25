package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@MastersEntityCustomAnnotation  (showOnFrontEnd = true)
@Table(name="m_nationality")
public class NationalityMaster extends BaseMaster {

    public NationalityMaster() {
    }
}
