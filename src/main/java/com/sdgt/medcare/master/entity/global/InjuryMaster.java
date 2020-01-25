package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_injury",schema = "adt")
public class InjuryMaster extends BaseMaster {

    public InjuryMaster() {
    }
}
