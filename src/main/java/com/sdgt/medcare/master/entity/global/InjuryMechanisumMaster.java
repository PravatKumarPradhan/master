package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_injury_mechanisum",schema = "adt")
public class InjuryMechanisumMaster extends BaseMaster {

    public InjuryMechanisumMaster() {
    }
}
