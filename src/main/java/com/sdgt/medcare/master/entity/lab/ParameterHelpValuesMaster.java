package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "m_param_help_values", schema = "lab")
public class ParameterHelpValuesMaster extends AbstractBaseEntity {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParameterHelpValuesMaster)) return false;
        if (!super.equals(o)) return false;
        ParameterHelpValuesMaster that = (ParameterHelpValuesMaster) o;
        return Objects.equals(getOrganizationMaster(), that.getOrganizationMaster()) &&
              Objects.equals(getParameterMaster(), that.getParameterMaster()) &&
              Objects.equals(getHelpValuesMaster(), that.getHelpValuesMaster());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOrganizationMaster(), getParameterMaster(), getHelpValuesMaster());
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parameter_master_id")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private ParameterMaster parameterMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="help_values_master_id")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private HelpValuesMaster helpValuesMaster;

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

    public HelpValuesMaster getHelpValuesMaster() {
        return helpValuesMaster;
    }

    public void setHelpValuesMaster(HelpValuesMaster helpValuesMaster) {
        this.helpValuesMaster = helpValuesMaster;
    }
}
