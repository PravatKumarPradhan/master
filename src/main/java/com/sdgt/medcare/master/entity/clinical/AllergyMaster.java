package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_allergy",schema = "clinical")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class AllergyMaster extends BaseMaster {

    @Column(name ="type")
    private String type;

    @Column(name ="guid")
    private String guid;

    @Column(name ="mimstype")
    private String mimstype;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getMimstype() {
        return mimstype;
    }

    public void setMimstype(String mimstype) {
        this.mimstype = mimstype;
    }
}
