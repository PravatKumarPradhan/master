package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="m_gender")
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class GenderMaster extends BaseMaster {

    @Column(name="gender_code",unique = true,length = 20)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String genderCode;

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public GenderMaster() {
    }

    public GenderMaster(String genderCode) {
        this.genderCode = genderCode;
    }


}
