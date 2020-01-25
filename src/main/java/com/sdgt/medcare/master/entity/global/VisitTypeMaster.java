package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_visit_type")
public class VisitTypeMaster extends BaseMaster {

   /* @Column(name="visit_type_code",length = 20)
    private String visitTypeCode;

    public String getVisitTypeCode() {
        return visitTypeCode;
    }

    public void setVisitTypeCode(String visitTypeCode) {
        this.visitTypeCode = visitTypeCode;
    }

    public VisitTypeMaster() {
    }

    public VisitTypeMaster(String visitTypeCode) {
        this.visitTypeCode = visitTypeCode;
    }*/

    public VisitTypeMaster() {
    }
}

