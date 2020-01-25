package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_categorymaster",schema = "clinical")
public class GenericCategoryMaster extends BaseMaster {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    private String type;
}