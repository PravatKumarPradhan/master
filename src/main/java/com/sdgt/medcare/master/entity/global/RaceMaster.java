package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_race")
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
public class RaceMaster extends BaseMaster {

    public RaceMaster() {
    }
}
