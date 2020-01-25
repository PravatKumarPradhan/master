package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "m_intervention",schema = "clinical")
public class InterventionMaster  extends BaseMaster implements Serializable {

    /**
     *
     */

    public InterventionMaster() {
    }

    @Column(name ="is_checked")
    private Boolean isChecked=false;

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        isChecked = isChecked;
    }
}
