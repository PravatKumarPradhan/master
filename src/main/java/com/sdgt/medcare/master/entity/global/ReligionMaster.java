package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_religion")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class ReligionMaster extends BaseMaster {

}
