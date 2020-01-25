package com.sdgt.medcare.master.entity.lab;

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

@Table(name = "m_param_textual_range", schema = "lab")
public class ParameterTextualRangeMaster extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parameter_master_id")
    private ParameterMaster parameterMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="age_group_master_id")
    private AgeGroupMaster ageGroupMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gender_master_id")
    private GenderMaster genderMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="textual_values_master_id")
    private TexualValuesMaster  texualValuesMaster;

    @Column(name = "remarks")
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

    public AgeGroupMaster getAgeGroupMaster() {
        return ageGroupMaster;
    }

    public void setAgeGroupMaster(AgeGroupMaster ageGroupMaster) {
        this.ageGroupMaster = ageGroupMaster;
    }

    public GenderMaster getGenderMaster() {
        return genderMaster;
    }

    public void setGenderMaster(GenderMaster genderMaster) {
        this.genderMaster = genderMaster;
    }

    public TexualValuesMaster getTexualValuesMaster() {
        return texualValuesMaster;
    }

    public void setTexualValuesMaster(TexualValuesMaster texualValuesMaster) {
        this.texualValuesMaster = texualValuesMaster;
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
        if (!(o instanceof ParameterTextualRangeMaster)) return false;
        if (!super.equals(o)) return false;
        ParameterTextualRangeMaster that = (ParameterTextualRangeMaster) o;
        return Objects.equals(getOrganizationMaster(), that.getOrganizationMaster()) &&
              Objects.equals(getParameterMaster(), that.getParameterMaster()) &&
              Objects.equals(getAgeGroupMaster(), that.getAgeGroupMaster()) &&
              Objects.equals(getGenderMaster(), that.getGenderMaster()) &&
              Objects.equals(getTexualValuesMaster(), that.getTexualValuesMaster()) &&
              Objects.equals(getRemarks(), that.getRemarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOrganizationMaster(), getParameterMaster(), getAgeGroupMaster(), getGenderMaster(), getTexualValuesMaster(), getRemarks());
    }
}
