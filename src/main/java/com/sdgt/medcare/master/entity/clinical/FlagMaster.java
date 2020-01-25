package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_flag",schema = "clinical")
public class FlagMaster extends BaseMaster {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
