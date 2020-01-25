package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_amulatory_status",schema = "adt")
public class AmulatoryStatusMaster extends BaseMaster {

    public AmulatoryStatusMaster() {
    }
}
