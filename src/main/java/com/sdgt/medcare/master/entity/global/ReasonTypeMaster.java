package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_reason_type", schema = "public")

@DynamicUpdate
public class ReasonTypeMaster extends BaseMaster {

    @Column(name="res_type_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String  resTypeCode;

    public String getResTypeCode() {
        return resTypeCode;
    }

    public void setResTypeCode(String resTypeCode) {
        this.resTypeCode = resTypeCode;
    }
}
