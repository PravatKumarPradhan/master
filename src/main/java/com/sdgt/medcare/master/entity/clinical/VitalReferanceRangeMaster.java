package com.sdgt.medcare.master.entity.clinical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.GenderMaster;
import com.sdgt.medcare.master.entity.lab.AgeGroupMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_vital_referance_range",schema = "clinical")
@Entity
@JsonDeserialize
public class VitalReferanceRangeMaster extends BaseMaster   {

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public VitalMaster getVitalMaster() {
        return vitalMaster;
    }

    public void setVitalMaster(VitalMaster vitalMaster) {
        this.vitalMaster = vitalMaster;
    }

    public GenderMaster getGenderMaster() {
        return genderMaster;
    }

    public void setGenderMaster(GenderMaster genderMaster) {
        this.genderMaster = genderMaster;
    }

    public VitalUnitMaster getVitalUnitMaster() {
        return vitalUnitMaster;
    }

    public void setVitalUnitMaster(VitalUnitMaster vitalUnitMaster) {
        this.vitalUnitMaster = vitalUnitMaster;
    }

    public AgeGroupMaster getAgeGroupMaster() {
        return ageGroupMaster;
    }

    public void setAgeGroupMaster(AgeGroupMaster ageGroupMaster) {
        this.ageGroupMaster = ageGroupMaster;
    }

    public Float getLessThan() {
        return lessThan;
    }

    public void setLessThan(Float lessThan) {
        this.lessThan = lessThan;
    }

    public Float getMoreThan() {
        return moreThan;
    }

    public void setMoreThan(Float moreThan) {
        this.moreThan = moreThan;
    }

    public Float getMinValue() {
        return minValue;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }

    public Float getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Float defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;


    @JsonIgnoreProperties("vitalReferanceRangeMasters")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vital_master_id")
    private VitalMaster vitalMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gender_master_id")
    private GenderMaster genderMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vitalUnit_master_id")
    private VitalUnitMaster vitalUnitMaster;

    @JsonIgnoreProperties("organizationMaster")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="age_group_master_id")
    private AgeGroupMaster ageGroupMaster;

    @Column(name = "less_than")
    private Float lessThan;

    @Column(name = "more_than")
    private Float moreThan;

    @Column(name = "min_value")
    private Float minValue;

    @Column(name = "max_value")
    private Float maxValue;

    @Column(name = "default_value")
    private Float defaultValue;

    @Column(name = "remarks", length = 255)
    private String remarks;

}
