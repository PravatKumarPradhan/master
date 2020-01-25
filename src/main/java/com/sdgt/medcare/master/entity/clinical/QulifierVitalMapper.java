package com.sdgt.medcare.master.entity.clinical;


import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_qualifier_vital_mapper",schema = "clinical")
@Entity
public class QulifierVitalMapper extends BaseMaster {

    public VitalMaster getVitalMaster() {
        return vitalMaster;
    }

    public void setVitalMaster(VitalMaster vitalMaster) {
        this.vitalMaster = vitalMaster;
    }

    public QualifierMaster getQualifierMaster() {
        return qualifierMaster;
    }

    public void setQualifierMaster(QualifierMaster qualifierMaster) {
        this.qualifierMaster = qualifierMaster;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vital_master_id")
    VitalMaster vitalMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualifier_master_id")
    QualifierMaster qualifierMaster;

}
