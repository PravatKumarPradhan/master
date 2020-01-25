package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_arrival_mode",schema = "adt")
public class ArrivalModeMaster extends BaseMaster {

    public ArrivalModeMaster() {
    }
}
