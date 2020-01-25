package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@MastersEntityCustomAnnotation(showOnFrontEnd = true)
@Table(name="m_prefix")
@Entity
public class PrefixMaster extends BaseMaster {

    @MastersFieldCustomAnnotation(displayName = "Prefix Code", sequence = 4,visibleToUser = false)
    @Column(name="prefix_code")
    private String prefixCode;

    @MastersFieldCustomAnnotation(displayName = "Gender", sequence = 3)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gender_id")
    private GenderMaster genderMaster;

    public String getNationalityCode() {
        return prefixCode;
    }

    public void setNationalityCode(String prefixCode) {
        this.prefixCode = prefixCode;
    }

    public PrefixMaster() {
    }

    public PrefixMaster(String prefixCode) {
        this.prefixCode = prefixCode;
    }

    public PrefixMaster(String prefixCode, GenderMaster genderMaster) {
        this.prefixCode = prefixCode;
        this.genderMaster = genderMaster;
    }

    public String getPrefixCode() {
        return prefixCode;
    }

    public void setPrefixCode(String prefixCode) {
        this.prefixCode = prefixCode;
    }

    public GenderMaster getGenderMaster() {
        return genderMaster;
    }

    public void setGenderMaster(GenderMaster genderMaster) {
        this.genderMaster = genderMaster;
    }
}
