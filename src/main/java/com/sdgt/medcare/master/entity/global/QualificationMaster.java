package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_qualification")
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
public class QualificationMaster extends BaseMaster {



    public QualificationMaster() {
    }
}

