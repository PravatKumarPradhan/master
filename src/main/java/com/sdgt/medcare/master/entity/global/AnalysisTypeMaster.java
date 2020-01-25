package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_analysis_type", schema = "inventory")
@SequenceGenerator(name = "analysis_type_seq", sequenceName = "inventory.analysis_type_seq", allocationSize = 1)
@DynamicUpdate
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class AnalysisTypeMaster extends BaseMaster {

    public AnalysisTypeMaster() {
    }
}
