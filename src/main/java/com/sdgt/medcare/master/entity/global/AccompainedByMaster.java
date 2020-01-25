package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_accompained_by",schema = "adt")
public class AccompainedByMaster extends BaseMaster {

    public AccompainedByMaster() {
    }
}
