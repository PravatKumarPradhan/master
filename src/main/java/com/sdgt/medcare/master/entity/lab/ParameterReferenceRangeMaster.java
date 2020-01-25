package com.sdgt.medcare.master.entity.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.GenderMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="m_param_refreng",schema = "lab")
public class ParameterReferenceRangeMaster extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parameter_master_id")
    private ParameterMaster parameterMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gender_master_id")
    private GenderMaster genderMaster;

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

    @Column(name = "remarks", length = 255)
    private String remarks;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public ParameterMaster getParameterMaster() {
        return parameterMaster;
    }

    public void setParameterMaster(ParameterMaster parameterMaster) {
        this.parameterMaster = parameterMaster;
    }

    public GenderMaster getGenderMaster() {
        return genderMaster;
    }

    public void setGenderMaster(GenderMaster genderMaster) {
        this.genderMaster = genderMaster;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParameterReferenceRangeMaster)) return false;
        if (!super.equals(o)) return false;
        ParameterReferenceRangeMaster that = (ParameterReferenceRangeMaster) o;
        return Objects.equals(getOrganizationMaster(), that.getOrganizationMaster()) &&
              Objects.equals(getParameterMaster(), that.getParameterMaster()) &&
              Objects.equals(getGenderMaster(), that.getGenderMaster()) &&
              Objects.equals(getAgeGroupMaster(), that.getAgeGroupMaster()) &&
              Objects.equals(getLessThan(), that.getLessThan()) &&
              Objects.equals(getMoreThan(), that.getMoreThan()) &&
              Objects.equals(getMinValue(), that.getMinValue()) &&
              Objects.equals(getMaxValue(), that.getMaxValue()) &&
              Objects.equals(getRemarks(), that.getRemarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOrganizationMaster(), getParameterMaster(), getGenderMaster(), getAgeGroupMaster(), getLessThan(), getMoreThan(), getMinValue(), getMaxValue(), getRemarks());
    }
}
