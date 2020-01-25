package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_icd",schema = "clinical")
public class ICDMaster extends BaseMaster {

    public Boolean getNotifiableDisease() {
        return notifiableDisease;
    }

    public void setNotifiableDisease(Boolean notifiableDisease) {
        this.notifiableDisease = notifiableDisease;
    }

    @Column(name ="notifiableDisease")
    private Boolean notifiableDisease=false;
}
